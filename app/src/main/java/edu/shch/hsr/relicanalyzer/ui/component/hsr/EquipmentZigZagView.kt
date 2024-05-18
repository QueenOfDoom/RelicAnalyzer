package edu.shch.hsr.relicanalyzer.ui.component.hsr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.times
import edu.shch.hsr.relicanalyzer.hsr.GenericRelicSlot
import edu.shch.hsr.relicanalyzer.ui.component.TextualImageButton
import edu.shch.hsr.relicanalyzer.util.DrawableResource

@Composable
fun EquipmentZigZagView(
    images: Array<DrawableResource>,
    texts: Array<GenericRelicSlot>,
    spacing: Dp,
    onClick: (GenericRelicSlot) -> Unit,
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier
) {
    val offset = 10.dp
    val half = images.size / 2
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.offset(x = offset)
        ) {
            for (i in 0 until half) {
                TextualImageButton(
                    image = images[i].id,
                    text = texts[i].text,
                    fontSize = 4.em,
                    onClick = { onClick(texts[i]) },
                    modifier = buttonModifier
                )
                if (i != half - 1) {
                    Spacer(modifier = Modifier.height(spacing))
                }
            }
        }
        Column(
            modifier = Modifier.offset(x = (-offset), y = 2 * spacing)
        ) {
            for (i in half until images.size) {
                TextualImageButton(
                    image = images[i].id,
                    text = texts[i].text,
                    fontSize = 4.em,
                    onClick = { onClick(texts[i]) },
                    modifier = buttonModifier
                )
                if (i != images.size - 1) {
                    Spacer(modifier = Modifier.height(spacing))
                }
            }
        }
    }
}