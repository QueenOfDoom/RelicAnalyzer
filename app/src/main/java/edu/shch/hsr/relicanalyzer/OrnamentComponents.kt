package edu.shch.hsr.relicanalyzer

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Ornament
import edu.shch.hsr.relicanalyzer.hsr.dynamic.SimulatedUniverse
import edu.shch.hsr.relicanalyzer.ui.theme.DarkLavender
import edu.shch.hsr.relicanalyzer.ui.theme.NormalLavender
import edu.shch.hsr.relicanalyzer.ui.theme.WiltingLavender

@Composable
fun OrnamentRowItem(ornament: Ornament, onClick: (Ornament) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            onClick = { onClick(ornament) },
            border = BorderStroke(4.dp, DarkLavender),
            modifier = Modifier.size(160.dp)
        ) {
            Image(
                painter = painterResource(
                    id = ornament.planarSphere
                ),
                contentDescription = stringResource(id = ornament.text),
                modifier = Modifier
                    .padding(5.dp)
                    .size(80.dp)
            )
        }
        Text(
            text = stringResource(id = ornament.text),
            textAlign = TextAlign.Center,
            color = NormalLavender,
            fontSize = 3.6.em,
            fontWeight = FontWeight.W600,
            modifier = Modifier
                .width(140.dp)
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun OrnamentRow(world: SimulatedUniverse, modifier: Modifier = Modifier, onClick: (Ornament) -> Unit) {
    if (world.sets.size == 2) {
        val firstOrnament = world.sets[0]
        val secondOrnament = world.sets[1]
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
        ) {
            OrnamentRowItem(ornament = firstOrnament, onClick)
            OrnamentRowItem(ornament = secondOrnament, onClick)
        }
    }
}

@Composable()
fun OrnamentsBySimulatedUniverseWorlds(
    modifier: Modifier = Modifier,
    onClick: (Ornament) -> Unit
) {
    val worlds = SimulatedUniverse.entries.filter { it.sets.size == 2 }.toList()
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.SpaceEvenly,
        columns = GridCells.Fixed(1),
        modifier = modifier
    ) {
        items(worlds.toTypedArray()) { world ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                OrnamentRow(
                    world = world,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                    onClick = onClick
                )
                HorizontalDivider(
                    thickness = 4.dp,
                    color = WiltingLavender,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
        }
    }
}

@Composable()
fun OrnamentButton(@DrawableRes img: Int, @StringRes text: Int) {
    ElevatedButton(
        onClick = { /*TODO*/ },
        border = BorderStroke(4.dp, DarkLavender),
        modifier = Modifier.size(180.dp),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = stringResource(id = text),
                modifier = Modifier.size(112.dp)
            )
            Text(
                text = stringResource(id = text),
                fontSize = 4.em,
            )
        }
    }
}

@Composable()
fun OrnamentSetDetails(
    ornament: Ornament,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = ornament.text),
            textAlign = TextAlign.Center,
            color = NormalLavender,
            fontSize = 7.em,
            fontWeight = FontWeight.Bold,
            lineHeight = 1.2.em,
            modifier = Modifier.padding(16.dp)
        )
        HorizontalDivider(thickness = 2.dp, color = WiltingLavender)
        LazyRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            items(Character.entries) { character ->
                if (character.ornaments.contains(ornament)) {
                    RelicCharacter(character)
                }
            }
        }
        HorizontalDivider(thickness = 2.dp, color = WiltingLavender)
        val spacing = 96.dp
        Row(
            modifier = Modifier.padding(top = spacing)
        ) {
            Column {
                OrnamentButton(img = ornament.planarSphere, text = R.string.ornament_sphere)
            }
            Column(
                modifier = Modifier.offset(y = 90.dp + spacing)
            ) {
                OrnamentButton(img = ornament.linkRope, text = R.string.ornament_rope)
            }
        }
    }
}

@Composable()
fun OrnamentView(
    dispatcher: OnBackPressedDispatcher,
    modifier: Modifier = Modifier,
    back: () -> Unit
) {
    var ornament: Ornament? by rememberSaveable { mutableStateOf(null) }

    dispatcher.addCallback {
        if (ornament != null) {
            ornament = null
        } else back()
    }

    if (ornament == null) {
        OrnamentsBySimulatedUniverseWorlds(
            modifier = modifier
        ) {
            ornament = it
        }
    } else {
        OrnamentSetDetails(
            ornament = ornament!!,
            modifier = modifier
        )
    }
}