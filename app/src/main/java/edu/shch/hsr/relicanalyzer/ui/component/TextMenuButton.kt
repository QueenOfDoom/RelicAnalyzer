package edu.shch.hsr.relicanalyzer.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily

@Composable
fun TextMenuButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier.padding(vertical = 10.dp),
    fontSize: TextUnit = 7.em
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.elevatedButtonColors(),
        border = ButtonDefaults.outlinedButtonBorder.copy(width = 4.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = text),
            fontSize = fontSize,
            fontFamily = SaibaFamily,
            modifier = textModifier
        )
    }
}