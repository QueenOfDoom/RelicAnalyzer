package edu.shch.hsr.relicanalyzer.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import edu.shch.hsr.relicanalyzer.R
import edu.shch.hsr.relicanalyzer.hsr.OrnamentSlot
import edu.shch.hsr.relicanalyzer.hsr.RelicSlot
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.hsr.dynamic.LightCone
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Ornament
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Relic
import edu.shch.hsr.relicanalyzer.ui.view.CharacterEntryView
import edu.shch.hsr.relicanalyzer.ui.view.LightConeEntryView
import edu.shch.hsr.relicanalyzer.ui.view.OrnamentEntryView
import edu.shch.hsr.relicanalyzer.ui.view.RelicEntryView

@Composable
fun Router(route: List<RouteItem>) {
    val last = route.last()

    if (last is EnumRouteItem<*>) {
        when (last.value) {
            is Character -> {

            }
            is LightCone -> {

            }
            is Relic -> {

            }
            is Ornament -> {

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
            R.string.relic -> { RelicEntryView() }
            R.string.ornament -> OrnamentEntryView()
            R.string.character -> CharacterEntryView(modifier = Modifier.fillMaxWidth())
            R.string.lightcone -> LightConeEntryView()
            else -> throw IllegalStateException("Came across illegal state transition.")
        }
    }
}