package edu.shch.hsr.relicanalyzer.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.shch.hsr.relicanalyzer.hsr.dynamic.SimulatedUniverse
import edu.shch.hsr.relicanalyzer.ui.component.LabeledImageButton

@Composable
fun OrnamentPair(world: SimulatedUniverse) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (set in world.sets) {
            LabeledImageButton(
                img = set.planarSphere,
                label = set.text,
                onClick = { /*TODO*/ },
                modifier = Modifier.width(160.dp).padding(4.dp),
                buttonModifier = Modifier.size(140.dp)
            )
        }
    }
}

@Composable
fun OrnamentEntryView() {
    val worlds = SimulatedUniverse.entries

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                .fillMaxWidth()
        ) {
            itemsIndexed(worlds) { i, world ->
                OrnamentPair(world = world)
                if (i != worlds.size - 1) {
                    HorizontalDivider(
                        thickness = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}