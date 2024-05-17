package edu.shch.hsr.relicanalyzer.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily

@Composable
fun ToggleSwitchTextButton(
    @StringRes firstText: Int,
    @StringRes secondText: Int,
    state: Boolean,
    setState: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    fontSize: TextUnit = 4.em
) {
    val selectedColors = ButtonDefaults.buttonColors().copy(
        containerColor = MaterialTheme.colorScheme.surfaceDim,
        contentColor = MaterialTheme.colorScheme.onSurface
    )
    val alternativeColors = ButtonDefaults.filledTonalButtonColors().copy(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.secondary
    )

    Row {
        Button(
            onClick = { setState(true) },
            colors = if (state) selectedColors else alternativeColors,
            border = if(state)
                ButtonDefaults.outlinedButtonBorder.copy(width = 4.dp)
            else null,
            modifier = modifier
        ) {
            Text(
                text = stringResource(id = firstText),
                fontSize = fontSize,
                fontFamily = SaibaFamily,
                modifier = textModifier
            )
        }

        Button(
            onClick = { setState(false) },
            colors = if (state) alternativeColors else selectedColors,
            border = if (state) null else
                ButtonDefaults.outlinedButtonBorder.copy(width = 4.dp),
            modifier = modifier
        ) {
            Text(
                text = stringResource(id = secondText),
                fontSize = fontSize,
                fontFamily = SaibaFamily,
                modifier = textModifier
            )
        }
    }
}