package edu.shch.hsr.relicanalyzer

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Ornament
import edu.shch.hsr.relicanalyzer.hsr.dynamic.SimulatedUniverse
import edu.shch.hsr.relicanalyzer.ui.theme.DarkLavender

@Composable()
fun OrnamentRow(world: SimulatedUniverse, modifier: Modifier = Modifier, onClick: (Ornament) -> Unit) {
    if (world.sets.size == 2) {
        val firstOrnament = world.sets[0]
        val secondOrnament = world.sets[1]
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
        ) {
            ElevatedButton(
                onClick = { onClick(firstOrnament) },
                border = BorderStroke(4.dp, DarkLavender),
                modifier = Modifier.size(160.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = firstOrnament.planarSphere
                    ),
                    contentDescription = stringResource(id = firstOrnament.text),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(80.dp)
                )
            }
            ElevatedButton(
                onClick = { onClick(secondOrnament) },
                border = BorderStroke(4.dp, DarkLavender),
                modifier = Modifier.size(160.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = secondOrnament.planarSphere
                    ),
                    contentDescription = stringResource(id = secondOrnament.text),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(80.dp)
                )
            }
        }
    }
}

@Composable()
fun OrnamentsBySimulatedUniverseWorlds() {
    val worlds = SimulatedUniverse.entries.filter { it.sets.size == 2 }.toList()
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.SpaceEvenly,
        columns = GridCells.Fixed(1),
        modifier = Modifier.padding(top = 60.dp)
    ) {
        items(worlds.toTypedArray()) { world ->
            OrnamentRow(
                world = world,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {  }
            HorizontalDivider(thickness = 2.dp, color = DarkLavender)
        }
    }
}

@Composable()
fun OrnamentView(dispatcher: OnBackPressedDispatcher, back: () -> Unit) {
    dispatcher.addCallback { back() }
    OrnamentsBySimulatedUniverseWorlds()
}