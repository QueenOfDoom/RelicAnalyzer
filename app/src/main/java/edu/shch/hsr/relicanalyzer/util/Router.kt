package edu.shch.hsr.relicanalyzer.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
import edu.shch.hsr.relicanalyzer.ui.view.list.CharacterListView
import edu.shch.hsr.relicanalyzer.ui.view.list.LightConeListView
import edu.shch.hsr.relicanalyzer.ui.view.list.OrnamentListView
import edu.shch.hsr.relicanalyzer.ui.view.list.RelicListView

@Composable
fun Router(route: List<RouteItem>, move: (RouteItem) -> Unit) {
    if (route.isEmpty()) {
        ChooseSubject(move, modifier = Modifier.fillMaxSize())
        return
    }

    val modifierWithOffset = Modifier
        .fillMaxSize()
        .padding(top = 64.dp)

    val last = route.last()
    if (last is EnumRouteItem<*>) {
        when (last.value) {
            is Character -> {

            }
            is LightCone -> {

            }
            is Relic -> {
                RelicSetDetailView(
                    relic = last.value,
                    modifier = modifierWithOffset
                )
            }
            is Ornament -> {
                OrnamentSetDetailView(
                    ornament = last.value,
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
            R.string.relic -> { RelicListView(move, modifierWithOffset) }
            R.string.ornament -> OrnamentListView(move, modifierWithOffset)
            R.string.character -> CharacterListView(modifierWithOffset)
            R.string.lightcone -> LightConeListView()
            else -> throw IllegalStateException("Came across illegal state transition.")
        }
    }
}