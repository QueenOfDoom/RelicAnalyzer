package edu.shch.hsr.relicanalyzer.ui.view.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.R
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Character
import edu.shch.hsr.relicanalyzer.ui.component.SearchBox
import edu.shch.hsr.relicanalyzer.ui.component.ToggleSwitchTextButton
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily
import edu.shch.hsr.relicanalyzer.util.LocaleRouteItem
import edu.shch.hsr.relicanalyzer.util.RouteItem

@Composable
@Preview
fun CharacterItem(
    modifier: Modifier = Modifier,
    clipShape: Shape = RoundedCornerShape(16.dp),
    character: Character = Character.FU_XUAN,
    onClick: () -> Unit = {}
) {
    var characterName = stringResource(id = character.charName)
        .replace(Regex("\\s*•\\s*"), "\n")
        .replace(Regex("\\s*\\(\\w+\\)"), "")

    // Special-Case Handling
    if (characterName == "Dan Heng\nImbibitor Lunae") {
        characterName = "Dang Heng IL"
    }

    Button(
        shape = clipShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        onClick = onClick
    ) {
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
}

@Composable
fun CharacterListView(
    forward: (RouteItem) -> Unit,
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
            openFilter = { forward(LocaleRouteItem.DoNotTouch) },
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
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            items(characters) {
                CharacterItem(
                    modifier = Modifier.background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.2f)
                            )
                        )
                    ),
                    clipShape = RoundedCornerShape(16.dp),
                    character = it,
                    onClick = { forward(LocaleRouteItem.DoNotTouch) }
                )
            }
        }
    }
}
