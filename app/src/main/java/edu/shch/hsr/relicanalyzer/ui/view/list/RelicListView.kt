package edu.shch.hsr.relicanalyzer.ui.view.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import edu.shch.hsr.relicanalyzer.hsr.dynamic.CavernOfCorrosion
import edu.shch.hsr.relicanalyzer.ui.component.LabeledImageButtonPairList
import edu.shch.hsr.relicanalyzer.util.EnumRouteItem
import edu.shch.hsr.relicanalyzer.util.RouteItem

@Composable
fun RelicListView(move: (RouteItem) -> Unit, modifier: Modifier = Modifier) {
    LabeledImageButtonPairList(
        list = CavernOfCorrosion.entries,
        rowExtractor = { it.sets },
        imageExtractor = { it.head },
        textExtractor = { it.text },
        onClick = { move(EnumRouteItem(it)) },
        modifier = modifier
    )
}