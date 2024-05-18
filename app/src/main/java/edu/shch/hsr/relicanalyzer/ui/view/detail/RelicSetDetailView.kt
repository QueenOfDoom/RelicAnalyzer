package edu.shch.hsr.relicanalyzer.ui.view.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.R
import edu.shch.hsr.relicanalyzer.hsr.RelicSlot
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Relic
import edu.shch.hsr.relicanalyzer.ui.component.TextMenuButton
import edu.shch.hsr.relicanalyzer.ui.component.hsr.EquipmentSetInfoDialogue
import edu.shch.hsr.relicanalyzer.ui.component.hsr.EquipmentUser
import edu.shch.hsr.relicanalyzer.ui.component.hsr.EquipmentZigZagView
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily
import edu.shch.hsr.relicanalyzer.util.LocaleRouteItem
import edu.shch.hsr.relicanalyzer.util.RouteItem
import edu.shch.hsr.relicanalyzer.util.asDrawableRes


@Composable
fun RelicSetDetailView(relic: Relic, forward: (RouteItem) -> Unit, modifier: Modifier = Modifier) {
    val openSetInfo = remember { mutableStateOf(false) }
    if (openSetInfo.value) EquipmentSetInfoDialogue(equipment = relic)
        { openSetInfo.value = false }
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
                EquipmentUser(character = it) {
                    forward(LocaleRouteItem.DoNotTouch)
                }
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
            onClick = { openSetInfo.value = true },
            fontSize = 4.em,
            modifier = Modifier
                .align(Alignment.End)
                .offset(x = (-30).dp, y = 40.dp),
            textModifier = Modifier.padding(2.dp)
        )
        EquipmentZigZagView(
            images = arrayOf(relic.head, relic.hands, relic.body, relic.feet)
                .map { it.asDrawableRes() }.toTypedArray(),
            texts = RelicSlot.entries.toTypedArray(),
            spacing = 60.dp,
            onClick = { forward(LocaleRouteItem.DoNotTouch) },
            modifier = Modifier.fillMaxWidth(),
            buttonModifier = Modifier.size(180.dp)
        )
    }
}