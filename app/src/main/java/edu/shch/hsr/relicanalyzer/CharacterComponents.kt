package edu.shch.hsr.relicanalyzer

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.hsr.Element
import edu.shch.hsr.relicanalyzer.hsr.GenericRelic
import edu.shch.hsr.relicanalyzer.hsr.Path
import edu.shch.hsr.relicanalyzer.hsr.Rarity
import edu.shch.hsr.relicanalyzer.hsr.Statistic
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character

@Composable
fun CharacterDetailView() {

}

@Composable
fun SwitchButton(
    @StringRes first: Int,
    @StringRes second: Int,
    isDetailed: Boolean,
    modifier: Modifier = Modifier,
    switcher: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        val buttonColors = ButtonDefaults.elevatedButtonColors()
        OutlinedButton(
            onClick = { switcher(0) },
            border = if (isDetailed)
                null
            else ButtonDefaults.outlinedButtonBorder.copy(width = 4.dp),
            colors = buttonColors.copy(
                containerColor = if (isDetailed)
                    buttonColors.containerColor
                else MaterialTheme.colorScheme.surfaceDim
            )
        ) {
            Text(
                text = stringResource(id = first),
                fontSize = 4.em,
                fontFamily = saibaFamily
            )
        }
        OutlinedButton(
            onClick = { switcher(1) },
            border = if (isDetailed)
                ButtonDefaults.outlinedButtonBorder.copy(width = 4.dp)
            else null,
            colors = buttonColors.copy(
                containerColor = if (isDetailed)
                    MaterialTheme.colorScheme.surfaceDim
                else buttonColors.containerColor
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListHeader(
    search:String,
    updateSearch: (String) -> Unit,
    detailed: Boolean,
    toggleDetail: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 80.dp)
    ) {
        Text(
            text = stringResource(id = R.string.character),
            fontFamily = saibaFamily,
            fontSize = 8.em
        )
        HorizontalDivider(
            thickness = 4.dp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(
                horizontal = 32.dp,
                vertical = 8.dp
            )
        )
        SwitchButton(
            first = R.string.ui_view_general,
            second = R.string.ui_view_detail,
            isDetailed = detailed,
            modifier = Modifier.padding(bottom = 4.dp),
            toggleDetail
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            DockedSearchBar(
                query = search,
                onQueryChange = updateSearch,
                colors = SearchBarDefaults.colors(
                    dividerColor = Color.Transparent
                ),
                onSearch = {},
                active = true,
                onActiveChange = {},
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                } },
                shape = SearchBarDefaults.inputFieldShape,
                modifier = Modifier
                    .height(64.dp)
                    .padding(vertical = 4.dp)
            ) {}
        }
        HorizontalDivider(
            thickness = 4.dp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(
                horizontal = 32.dp,
                vertical = 8.dp
            )
        )
    }
}

@Composable
fun DetailedCharacterList(
    search: String,
    updateSearch: (String) -> Unit,
    detailed: Boolean,
    toggleDetail: (Int) -> Unit
) {
    CharacterListHeader(search, updateSearch, detailed, toggleDetail)
}

@Composable
fun CharacterListItemView(character: Character, display: Boolean = true) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = if(display)
            Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(4.dp)
        else Modifier
    ) {
        Image(
            painter = painterResource(id = character.icon),
            alpha = if (display) 1f else 0f,
            contentDescription = null,
        )
        Text(
            text = if (display) stringResource(id = character.charName) else "",
            textAlign = TextAlign.Center
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = character.path.image),
                alpha = if (display) 1f else 0f,
                contentDescription = null,
                modifier = Modifier.height(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = if (display) stringResource(id = character.path.text) else "")
        }
    }
}

@Composable
fun GeneralCharacterList(
    search: String,
    updateSearch: (String) -> Unit,
    detailed: Boolean,
    toggleDetail: (Int) -> Unit
) {
    CharacterListHeader(search, updateSearch, detailed, toggleDetail)
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 5.dp),
        modifier = Modifier.offset(y = 280.dp)
    ) {
        val characters = Character.entries
        items(characters) {
            CharacterListItemView(character = it)
        }
        items(6) {
            CharacterListItemView(character = characters[it], display = false)
        }
    }
}

@Composable
fun CharacterView(dispatcher: OnBackPressedDispatcher, back: () -> Unit) {
    var character: Character? by rememberSaveable { mutableStateOf(null) }

    var search: String by rememberSaveable { mutableStateOf("") }
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
            DetailedCharacterList(
                search,
                { search = it },
                true
            ) { num -> detailed = num == 1 }
        } else {
            GeneralCharacterList(
                search,
                { search = it },
                false
            ) {  num -> detailed = num == 1 }
        }
    }
}