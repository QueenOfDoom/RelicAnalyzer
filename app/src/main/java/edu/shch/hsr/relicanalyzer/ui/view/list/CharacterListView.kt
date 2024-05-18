package edu.shch.hsr.relicanalyzer.ui.view.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.R
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.ui.component.ToggleSwitchTextButton
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily

@Composable
@Preview
fun CharacterItem(
    modifier: Modifier = Modifier,
    clipShape: Shape = RoundedCornerShape(16.dp),
    character: Character = Character.FU_XUAN
) {
    var characterName = stringResource(id = character.charName)
        .replace(Regex("\\s*•\\s*"), "\n")
        .replace(Regex("\\s*\\(\\w+\\)"), "")

    // Special-Case Handling
    if (characterName == "Dan Heng\nImbibitor Lunae") {
        characterName = "Dang Heng IL"
    }

    Box(
        modifier = modifier
            .clip(clipShape)
            .height(160.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.surfaceDim,
                shape = clipShape
            )
    ) {
        Image(
            painter = painterResource(id = character.icon),
            contentDescription = stringResource(id = character.charName),
            modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = characterName,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = character.path.image),
                    contentDescription = stringResource(id = character.path.text),
                    modifier = Modifier.size(16.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = character.path.text),
                    fontSize = 3.em
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchBox(
    search: String,
    changeSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val source = remember { MutableInteractionSource() }
    val textFieldColors = OutlinedTextFieldDefaults.colors()
    BasicTextField(
        value = search,
        onValueChange = changeSearch,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 4.em,
            color = MaterialTheme.colorScheme.primary
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        modifier = modifier,
        decorationBox = @Composable { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = search,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                isError = false,
                interactionSource = source,
                colors = textFieldColors,
                contentPadding = PaddingValues(start = 16.dp),
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = true,
                        isError = false,
                        interactionSource = source,
                        colors = textFieldColors,
                        shape = CircleShape
                    )
                }
            )
        }
    )
}

@Composable
fun CharacterListView(
    modifier: Modifier = Modifier
) {
    var isGeneral: Boolean by rememberSaveable { mutableStateOf(true) }
    var search: String by rememberSaveable { mutableStateOf("") }

    val characters = Character.entries.filter {
        stringResource(id = it.charName).contains(search)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.character),
            fontSize = 7.em,
            fontFamily = SaibaFamily,
        )
        HorizontalDivider(
            thickness = 4.dp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 12.dp)
        )
        ToggleSwitchTextButton(
            firstText = R.string.ui_view_general,
            secondText = R.string.ui_view_detail,
            state = isGeneral,
            setState = { bool -> isGeneral = bool }
        )
        SearchBox(
            search = search,
            changeSearch = { search = it },
            modifier = Modifier
                .padding(
                    top = 4.dp, bottom = 16.dp,
                    start = 8.dp, end = 8.dp
                )
                .height(36.dp)
                .fillMaxWidth(0.7f)
        )
        HorizontalDivider(
            thickness = 4.dp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 12.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 96.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            items(characters) {
                CharacterItem(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(colors = listOf(
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.2f),
                                Color.Transparent
                            ))
                        ),
                    clipShape = RoundedCornerShape(16.dp),
                    character = it
                )
            }
        }
    }
}
