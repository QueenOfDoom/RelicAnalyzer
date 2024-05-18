package edu.shch.hsr.relicanalyzer.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.R
import edu.shch.hsr.relicanalyzer.ui.component.IconImageButton
import edu.shch.hsr.relicanalyzer.ui.component.TextMenuButton
import edu.shch.hsr.relicanalyzer.ui.component.TextualImageButton
import edu.shch.hsr.relicanalyzer.util.LocaleRouteItem
import edu.shch.hsr.relicanalyzer.util.RouteItem
import edu.shch.hsr.relicanalyzer.util.reverseOn

@Composable
fun ChooseRelicOrnament(
    route: (RouteItem) -> Unit,
) {
    var isRelic: Boolean by rememberSaveable { mutableStateOf(true) }

    val (image, text) = arrayOf(
        (R.drawable.relic_117_0 to R.drawable.ornament_311_0),
        (R.string.relic to R.string.ornament)
    ).map { it.reverseOn(!isRelic) }.toTypedArray()

    Row(
        verticalAlignment = Alignment.Bottom,
    ) {
        TextualImageButton(
            image = image.first,
            text = text.first,
            fontSize = 5.6.em,
            onClick = { route(LocaleRouteItem(text.first)) },
            modifier = Modifier.size(196.dp, 294.dp),
            imageModifier = Modifier
                .padding(vertical = 5.dp)
                .size(128.dp)
        )
        IconImageButton(
            image = R.drawable.swap_horiz,
            onClick = { isRelic = !isRelic },
            modifier = Modifier
                .offset((-10).dp, 0.dp)
                .scale(1.25f)
        )
        TextualImageButton(
            image = image.second,
            text = text.second,
            fontSize = 2.em,
            onClick = { route(LocaleRouteItem(text.second)) },
            modifier = Modifier.size(105.dp, 130.dp),
            enabled = false,
            imageModifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp)
                .size(92.dp),
            textModifier = Modifier.offset(y = (-10).dp)
        )
    }
}

@Composable
fun ChooseSubject(
    route: (RouteItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        ChooseRelicOrnament(route)
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            TextMenuButton(
                text = R.string.character,
                onClick = { route(LocaleRouteItem.Character) },
                modifier = Modifier.fillMaxWidth()
            )
            TextMenuButton(
                text = R.string.lightcone,
                onClick = { route(LocaleRouteItem.LightCone) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}