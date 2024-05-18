package edu.shch.hsr.relicanalyzer.ui.view.detail

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.times
import edu.shch.hsr.relicanalyzer.R
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Relic
import edu.shch.hsr.relicanalyzer.ui.component.TextMenuButton
import edu.shch.hsr.relicanalyzer.ui.component.TextualImageButton
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily

@Composable
fun RelicSetUser(character: Character) {
    Image(
        painter = painterResource(id = character.icon),
        contentDescription = stringResource(id = character.charName),
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = CircleShape
            )
    )
}

@Composable
fun RelicSetDetailView(relic: Relic, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = relic.text),
            textAlign = TextAlign.Center,
            lineHeight = 1.2.em,
            fontSize = 7.em,
            fontFamily = SaibaFamily,
        )
        HorizontalDivider(
            thickness = 4.dp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 12.dp)
        )
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val users = Character.entries.filter { character ->
                // TODO: Maybe highlight those who prefer to use
                //       a 4-set version of the set differently?
                character.relics.any {
                    it.first == relic || it.second == relic
                }
            }
            items(users) {
                RelicSetUser(character = it)
            }
        }
        HorizontalDivider(
            thickness = 4.dp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 12.dp)
        )
        TextMenuButton(
            text = R.string.ui_set_info,
            onClick = {},
            fontSize = 4.em,
            modifier = Modifier
                .align(Alignment.End)
                .offset(x = (-30).dp, y = 40.dp),
            textModifier = Modifier.padding(2.dp)
        )

        val size = 160.dp
        val spacing = 45.dp
        val relicButtonModifier = Modifier.size(size)

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                TextualImageButton(
                    image = relic.head,
                    text = R.string.relic_head,
                    fontSize = 4.em,
                    onClick = { /*TODO*/ },
                    modifier = relicButtonModifier
                )
                Spacer(modifier = Modifier.height(spacing))
                TextualImageButton(
                    image = relic.body,
                    text = R.string.relic_body,
                    fontSize = 4.em,
                    onClick = { /*TODO*/ },
                    modifier = relicButtonModifier
                )
            }
            Column(
                modifier = Modifier.offset(y = 2 * spacing)
            ) {
                TextualImageButton(
                    image = relic.hands,
                    text = R.string.relic_hands,
                    fontSize = 4.em,
                    onClick = { /*TODO*/ },
                    modifier = relicButtonModifier
                )
                Spacer(modifier = Modifier.height(spacing))
                TextualImageButton(
                    image = relic.feet,
                    text = R.string.relic_feet,
                    fontSize = 4.em,
                    onClick = { /*TODO*/ },
                    modifier = relicButtonModifier
                )
            }
        }
    }
}