package edu.shch.hsr.relicanalyzer.util

import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import edu.shch.hsr.relicanalyzer.R
import edu.shch.hsr.relicanalyzer.hsr.OrnamentSlot
import edu.shch.hsr.relicanalyzer.hsr.RelicSlot
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.hsr.dynamic.LightCone
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Ornament
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Relic
import edu.shch.hsr.relicanalyzer.ui.view.ChooseSubject
import edu.shch.hsr.relicanalyzer.ui.view.detail.OrnamentSetDetailView
import edu.shch.hsr.relicanalyzer.ui.view.detail.RelicSetDetailView
import edu.shch.hsr.relicanalyzer.ui.view.dialogue.WorkInProgress
import edu.shch.hsr.relicanalyzer.ui.view.list.CharacterListView
import edu.shch.hsr.relicanalyzer.ui.view.list.LightConeListView
import edu.shch.hsr.relicanalyzer.ui.view.list.OrnamentListView
import edu.shch.hsr.relicanalyzer.ui.view.list.RelicListView

@Composable
fun Router(
    route: List<RouteItem>,
    forward: (RouteItem) -> Unit,
    backward: () -> Unit
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    if (route.isEmpty()) {
        ChooseSubject(forward, modifier = Modifier.fillMaxSize())
        return
    }

    var last = route.last()
    val modifierWithOffset = Modifier
        .fillMaxSize()
        .padding(top = 64.dp)

    if (last is LocaleRouteItem && last.id == R.string.ui_do_not_touch) {
        WorkInProgress(imageLoader = imageLoader, context = context, dismiss = backward)
        if (route.size == 1) {
            ChooseSubject(forward, modifier = Modifier.fillMaxSize())
            return
        } else {
            last = route[route.size - 2]
        }
    }

    if (last is EnumRouteItem<*>) {
        when (val enumItem = last.value) {
            is Character -> {

            }
            is LightCone -> {

            }
            is Relic -> {
                RelicSetDetailView(
                    relic = enumItem,
                    modifier = modifierWithOffset
                )
            }
            is Ornament -> {
                OrnamentSetDetailView(
                    ornament = enumItem,
                    modifier = modifierWithOffset
                )
            }
            is RelicSlot -> {
                if (route.size == 1) throw IllegalStateException("Cannot possibly be in 'Specific Relic' view.")
                @Suppress("UNUSED_VARIABLE") val previous = route[route.lastIndex - 1]
            }
            is OrnamentSlot -> {
                if (route.size == 1) throw IllegalStateException("Cannot possibly be in 'Specific Ornament' view.")
                @Suppress("UNUSED_VARIABLE") val previous = route[route.lastIndex - 1]
            }
        }
    } else if (last is LocaleRouteItem) {
        when (last.id) {
            R.string.relic -> { RelicListView(forward, modifierWithOffset) }
            R.string.ornament -> OrnamentListView(forward, modifierWithOffset)
            R.string.character -> CharacterListView(forward, modifierWithOffset)
            R.string.lightcone -> LightConeListView(forward, modifierWithOffset)
            else -> throw IllegalStateException("Came across illegal state transition.")
        }
    }
}