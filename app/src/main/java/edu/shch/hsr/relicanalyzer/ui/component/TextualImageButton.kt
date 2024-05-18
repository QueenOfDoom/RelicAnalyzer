package edu.shch.hsr.relicanalyzer.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily

@Composable
fun TextualImageButton(
    @DrawableRes image: Int,
    @StringRes text: Int,
    fontSize: TextUnit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.elevatedButtonColors(),
        border = ButtonDefaults.outlinedButtonBorder.copy(width = 4.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = text),
                modifier = imageModifier
            )
            Text(
                text = stringResource(id = text),
                textAlign = TextAlign.Center,
                lineHeight = 1.em,
                fontSize = fontSize,
                fontFamily = SaibaFamily,
                modifier = textModifier,
            )
        }
    }
}