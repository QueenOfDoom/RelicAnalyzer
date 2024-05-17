package edu.shch.hsr.relicanalyzer.ui.view

import androidx.compose.runtime.Composable
import edu.shch.hsr.relicanalyzer.hsr.dynamic.SimulatedUniverse
import edu.shch.hsr.relicanalyzer.ui.component.LabeledImageButtonPairList

@Composable
fun OrnamentEntryView() {
    LabeledImageButtonPairList(
        list = SimulatedUniverse.entries,
        rowExtractor = { it.sets },
        imageExtractor = { it.planarSphere },
        textExtractor = { it.text },
        {}
    )
}