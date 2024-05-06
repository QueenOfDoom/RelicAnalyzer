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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.shch.hsr.relicanalyzer.hsr.CavernOfCorrosion
import edu.shch.hsr.relicanalyzer.hsr.Relic
import edu.shch.hsr.relicanalyzer.ui.theme.DarkLavender

@Composable()
fun RelicRow(cavernOfCorrosion: CavernOfCorrosion, modifier: Modifier = Modifier, onClick: (Relic) -> Unit) {
    if (cavernOfCorrosion.sets.size == 2) {
        val firstRelic = cavernOfCorrosion.sets[0]
        val secondRelic = cavernOfCorrosion.sets[1]
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
        ) {
            ElevatedButton(
                onClick = { onClick(firstRelic) },
                border = BorderStroke(4.dp, DarkLavender),
                modifier = Modifier.size(160.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = firstRelic.head
                    ),
                    contentDescription = stringResource(id = firstRelic.text),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(80.dp)
                )
            }
            ElevatedButton(
                onClick = { onClick(secondRelic) },
                border = BorderStroke(4.dp, DarkLavender),
                modifier = Modifier.size(160.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = secondRelic.head
                    ),
                    contentDescription = stringResource(id = secondRelic.text),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(80.dp)
                )
            }
        }
    }
}

@Composable()
fun RelicsByCavernsOfCorrosion(dispatcher: OnBackPressedDispatcher, back: () -> Unit) {
    val caverns = CavernOfCorrosion.entries.filter { it.sets.size == 2 }.toList()
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.SpaceEvenly,
        columns = GridCells.Fixed(1),
        modifier = Modifier.padding(top = 60.dp)
    ) {
        dispatcher.addCallback { back() }

        itemsIndexed(caverns.toTypedArray()) { i, cavern ->
            RelicRow(
                cavernOfCorrosion = cavern,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {  }
            HorizontalDivider(thickness = 2.dp, color = DarkLavender)
        }
    }
}