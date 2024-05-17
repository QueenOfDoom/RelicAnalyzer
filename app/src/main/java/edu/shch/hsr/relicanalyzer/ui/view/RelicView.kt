package edu.shch.hsr.relicanalyzer.ui.view

import androidx.compose.runtime.Composable
import edu.shch.hsr.relicanalyzer.hsr.dynamic.CavernOfCorrosion
import edu.shch.hsr.relicanalyzer.ui.component.LabeledImageButtonPairList

@Composable
fun RelicEntryView() {
    LabeledImageButtonPairList(
        list = CavernOfCorrosion.entries,
        rowExtractor = { it.sets },
        imageExtractor = { it.head },
        textExtractor = { it.text },
        {}
    )
}