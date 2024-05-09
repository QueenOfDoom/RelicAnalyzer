package edu.shch.hsr.relicanalyzer

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.hsr.dynamic.CavernOfCorrosion
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Relic
import edu.shch.hsr.relicanalyzer.ui.theme.DarkLavender
import edu.shch.hsr.relicanalyzer.ui.theme.NormalLavender
import edu.shch.hsr.relicanalyzer.ui.theme.WiltingLavender

@Composable
fun RelicRowItem(relic: Relic, onClick: (Relic) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            onClick = { onClick(relic) },
            border = BorderStroke(4.dp, DarkLavender),
            modifier = Modifier.size(160.dp)
        ) {
            Image(
                painter = painterResource(
                    id = relic.head
                ),
                contentDescription = stringResource(id = relic.text),
                modifier = Modifier
                    .padding(5.dp)
                    .size(80.dp)
            )
        }
        Text(
            text = stringResource(id = relic.text),
            textAlign = TextAlign.Center,
            color = NormalLavender,
            fontSize = 3.6.em,
            fontWeight = FontWeight.W600,
            modifier = Modifier.width(140.dp)
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun RelicRow(cavernOfCorrosion: CavernOfCorrosion, modifier: Modifier = Modifier, onClick: (Relic) -> Unit) {
    if (cavernOfCorrosion.sets.size == 2) {
        val firstRelic = cavernOfCorrosion.sets[0]
        val secondRelic = cavernOfCorrosion.sets[1]
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
        ) {
            RelicRowItem(relic = firstRelic, onClick)
            RelicRowItem(relic = secondRelic, onClick)
        }
    }
}

@Composable()
fun RelicsByCavernsOfCorrosion(
    modifier: Modifier = Modifier,
    onClick: (Relic) -> Unit
) {
    val caverns = CavernOfCorrosion.entries.filter { it.sets.size == 2 }.toList()
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.SpaceEvenly,
        columns = GridCells.Fixed(1),
        modifier = modifier.padding(4.dp)
    ) {
        items(caverns) { cavern ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                RelicRow(
                    cavernOfCorrosion = cavern,
                    modifier = Modifier.padding(vertical = 10.dp)
                        .fillMaxWidth()
                ) { onClick(it) }
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
fun RelicButton(@DrawableRes img: Int, @StringRes text: Int) {
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
fun RelicCharacter(character: Character) {
    Image(
        painter = painterResource(id = character.icon),
        contentDescription = stringResource(id = character.charName),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .border(1.dp, DarkLavender, shape = CircleShape)
            .clipToBounds()
    )
}

@Composable()
fun RelicSetDetails(
    relic: Relic,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = relic.text),
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
                if (character.relics.any { (first, second) ->
                    first == relic || second == relic
                }) {
                    RelicCharacter(character)
                }
            }
        }
        HorizontalDivider(thickness = 2.dp, color = WiltingLavender)
        val spacing = 32.dp
        Row(
            modifier = Modifier.padding(top = spacing)
        ) {
            Column {
                RelicButton(img = relic.head, text = R.string.relic_head)
                Spacer(modifier = Modifier.height(spacing))
                RelicButton(img = relic.body, text = R.string.relic_body)
            }
            Column(
                modifier = Modifier.offset(y = 90.dp + spacing / 2)
            ) {
                RelicButton(img = relic.hands, text = R.string.relic_hands)
                Spacer(modifier = Modifier.height(spacing))
                RelicButton(img = relic.feet, text = R.string.relic_feet)
            }
        }
    }
}

@Composable()
fun RelicView(
    dispatcher: OnBackPressedDispatcher,
    modifier: Modifier = Modifier,
    back: () -> Unit
) {
    var relic: Relic? by rememberSaveable { mutableStateOf(null) }

    dispatcher.addCallback {
        if (relic != null) {
            relic = null
        } else back()
    }

    if (relic == null) {
        RelicsByCavernsOfCorrosion(
            modifier = modifier
        ) { relic = it }
    } else {
        RelicSetDetails(
            relic = relic!!,
            modifier = modifier
        )
    }
}