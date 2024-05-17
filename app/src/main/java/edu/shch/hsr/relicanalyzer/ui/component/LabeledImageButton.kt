package edu.shch.hsr.relicanalyzer.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily

@Composable
fun LabeledImageButton(
    @DrawableRes img: Int,
    @StringRes label: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.elevatedButtonColors(),
            border = ButtonDefaults.outlinedButtonBorder.copy(width = 4.dp),
            modifier = buttonModifier
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = stringResource(id = label)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = label),
            textAlign = TextAlign.Center,
            fontFamily = SaibaFamily
        )
    }
}