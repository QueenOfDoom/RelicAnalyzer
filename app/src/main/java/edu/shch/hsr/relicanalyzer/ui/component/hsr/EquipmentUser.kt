package edu.shch.hsr.relicanalyzer.ui.component.hsr

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character

@Composable
fun EquipmentUser(character: Character) {
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
