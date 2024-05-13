package edu.shch.hsr.relicanalyzer.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.saibaFamily

@Composable
fun TextMenuButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        border = ButtonDefaults.outlinedButtonBorder.copy(width = 4.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = text),
            fontSize = 7.em,
            fontFamily = saibaFamily,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}