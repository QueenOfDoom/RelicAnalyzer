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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.window.Dialog
import edu.shch.hsr.relicanalyzer.hsr.OrnamentSlot
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Ornament
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Relic
import edu.shch.hsr.relicanalyzer.hsr.dynamic.SimulatedUniverse
import edu.shch.hsr.relicanalyzer.ui.theme.DarkLavender
import edu.shch.hsr.relicanalyzer.ui.theme.NormalLavender
import edu.shch.hsr.relicanalyzer.ui.theme.OverlayLavender
import edu.shch.hsr.relicanalyzer.ui.theme.PlainAssWhite
import edu.shch.hsr.relicanalyzer.ui.theme.WhiteLavender
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
            fontFamily = saibaFamily,
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
fun OrnamentButton(@DrawableRes img: Int, @StringRes text: Int, onClick: () -> Unit) {
    ElevatedButton(
        onClick = { onClick() },
        border = BorderStroke(4.dp, DarkLavender),
        modifier = Modifier.size(180.dp)
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
                textAlign = TextAlign.Center,
                fontFamily = saibaFamily,
                fontSize = 4.em,
                //modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
fun OrnamentSetDetailDialog(ornament: Ornament, onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest,
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(4.dp, WiltingLavender),
            colors = CardDefaults.cardColors().copy(
                containerColor = OverlayLavender,
                contentColor = PlainAssWhite
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(500.dp)
                .requiredHeight(460.dp)
                .offset(y = 40.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxHeight(1f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .height(420.dp)
                    .padding(20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.ui_set_ornament),
                    fontSize = 4.3.em,
                    fontFamily = saibaFamily,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = ornament.set),
                    fontSize = 3.8.em,
                    textAlign = TextAlign.Center,
                    color = WhiteLavender
                )
            }
        }
    }
}

@Composable()
fun OrnamentSetDetails(
    ornament: Ornament,
    modifier: Modifier = Modifier,
    onClick: (OrnamentSlot) -> Unit
) {
    val openSetInfo = remember { mutableStateOf(false) }
    if (openSetInfo.value) {
        OrnamentSetDetailDialog(
            ornament = ornament
        ) {
            openSetInfo.value = false
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = ornament.text),
            textAlign = TextAlign.Center,
            color = NormalLavender,
            fontSize = 7.em,
            fontFamily = saibaFamily,
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
        ElevatedButton(
            onClick = { openSetInfo.value = true },
            border = BorderStroke(2.dp, DarkLavender),
            modifier = Modifier
                .align(Alignment.End)
                .height(40.dp)
                .offset(x = (-30).dp, y = 60.dp)
        ) {
            Text(
                text = stringResource(id = R.string.ui_set_info),
                fontFamily = saibaFamily,
                fontSize = 4.em
            )
        }
        Row(
            modifier = Modifier.padding(top = spacing - 40.dp)
        ) {
            Column {
                OrnamentButton(img = ornament.planarSphere, text = R.string.ornament_sphere) {
                    onClick(OrnamentSlot.PlanarSphere)
                }
            }
            Column(
                modifier = Modifier.offset(y = 90.dp + spacing)
            ) {
                OrnamentButton(img = ornament.linkRope, text = R.string.ornament_rope) {
                    onClick(OrnamentSlot.LinkRope)
                }
            }
        }
    }
}

@Composable()
fun OrnamentView(
    dispatcher: OnBackPressedDispatcher,
    showMaintenanceDialogue: () -> Unit,
    modifier: Modifier = Modifier,
    back: () -> Unit
) {
    var ornament: Ornament? by rememberSaveable { mutableStateOf(null) }
    var ornamentSlot: OrnamentSlot? by rememberSaveable { mutableStateOf(null) }

    dispatcher.addCallback {
        if (ornamentSlot != null) {
            ornamentSlot = null
        } else if (ornament != null) {
            ornament = null
        } else back()
    }

    if (ornament == null) {
        OrnamentsBySimulatedUniverseWorlds(
            modifier = modifier
        ) {
            ornament = it
        }
    } else if (ornamentSlot == null) {
        OrnamentSetDetails(
            ornament = ornament!!,
            modifier = modifier
        ) { ornamentSlot = it }
    } else {
        ornamentSlot = null
        showMaintenanceDialogue()
    }
}