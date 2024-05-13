package edu.shch.hsr.relicanalyzer.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun IconImageButton(
    @DrawableRes image: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = image),
            contentDescription = description
        )
    }
}