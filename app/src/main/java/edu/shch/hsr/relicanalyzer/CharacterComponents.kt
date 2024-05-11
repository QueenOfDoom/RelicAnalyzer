package edu.shch.hsr.relicanalyzer

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.hsr.Element
import edu.shch.hsr.relicanalyzer.hsr.GenericRelic
import edu.shch.hsr.relicanalyzer.hsr.Path
import edu.shch.hsr.relicanalyzer.hsr.Rarity
import edu.shch.hsr.relicanalyzer.hsr.Statistic
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.ui.theme.DarkLavender
import edu.shch.hsr.relicanalyzer.ui.theme.WiltingLavender

@Composable
fun CharacterDetailView() {

}

@Composable
fun SwitchButton(
    @StringRes first: Int,
    @StringRes second: Int,
    isDetailed: Boolean,
    switcher: (Int) -> Unit
) {
    val buttonColors = ButtonDefaults.elevatedButtonColors()

    Row(
        horizontalArrangement = Arrangement.Center,
        //modifier = Modifier.background(WiltingLavender, CircleShape)
    ) {
        ElevatedButton(
            onClick = { switcher(0) },
            border = if (isDetailed) null else BorderStroke(4.dp, WiltingLavender),
            colors = buttonColors.copy(
                containerColor = if (isDetailed) buttonColors.containerColor else DarkLavender
            )
        ) {
            Text(
                text = stringResource(id = first),
                fontSize = 4.em,
                fontFamily = saibaFamily
            )
        }
        ElevatedButton(
            onClick = { switcher(1) },
            border = if (isDetailed) BorderStroke(4.dp, WiltingLavender) else null,
            colors = buttonColors.copy(
                containerColor = if (isDetailed) DarkLavender else buttonColors.containerColor
            )
        ) {
            Text(
                text = stringResource(id = second),
                fontSize = 4.em,
                fontFamily = saibaFamily
            )
        }
    }
}

@Composable
fun CharacterListHeader(detailed: Boolean, toggleDetail: (Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 80.dp)
    ) {
        Text(
            text = stringResource(id = R.string.character),
            fontFamily = saibaFamily,
            fontSize = 8.em
        )
        HorizontalDivider(
            thickness = 4.dp,
            color = WiltingLavender,
            modifier = Modifier.padding(
                horizontal = 32.dp,
                vertical = 8.dp
            )
        )
        SwitchButton(
            first = R.string.ui_view_general,
            second = R.string.ui_view_detail,
            isDetailed = detailed,
            toggleDetail
        )
        HorizontalDivider(
            thickness = 4.dp,
            color = WiltingLavender,
            modifier = Modifier.padding(
                horizontal = 32.dp,
                vertical = 8.dp
            )
        )
    }
}

@Composable
fun DetailedCharacterList(detailed: Boolean, toggleDetail: (Int) -> Unit) {
    CharacterListHeader(detailed, toggleDetail)
}

@Composable
fun GeneralCharacterList(detailed: Boolean, toggleDetail: (Int) -> Unit) {
    CharacterListHeader(detailed, toggleDetail)
}

@Composable
fun CharacterView(dispatcher: OnBackPressedDispatcher, back: () -> Unit) {
    var character: Character? by rememberSaveable { mutableStateOf(null) }

    var search: String? by rememberSaveable { mutableStateOf("") }
    var detailed: Boolean by rememberSaveable { mutableStateOf(false) }

    /* Filters */
    val pathFilter: List<Path> by rememberSaveable { mutableStateOf(listOf()) }
    val elementFilter: List<Element> by rememberSaveable { mutableStateOf(listOf()) }
    val rarityFilter: Rarity? by rememberSaveable { mutableStateOf(null) }
    /* Detailed View Filters */
    val relicFilter: List<GenericRelic> by rememberSaveable { mutableStateOf(listOf()) }
    val statFilter: List<Statistic> by rememberSaveable { mutableStateOf(listOf()) }

    dispatcher.addCallback {
        if (character != null) {
            character = null
        } else back()
    }

    if (character == null) {
        if (detailed) {
            DetailedCharacterList(true) { num -> detailed = num == 1 }
        } else {
            GeneralCharacterList(false) {  num -> detailed = num == 1 }
        }
    }
}