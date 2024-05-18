package edu.shch.hsr.relicanalyzer.ui.view.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.R
import edu.shch.hsr.relicanalyzer.hsr.dynamic.LightCone
import edu.shch.hsr.relicanalyzer.ui.component.SearchBox
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily
import edu.shch.hsr.relicanalyzer.util.LocaleRouteItem
import edu.shch.hsr.relicanalyzer.util.RouteItem
import edu.shch.hsr.relicanalyzer.util.coalesce

@Composable
fun LightConeItem(
    lightCone: LightCone,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .height(160.dp)
        .border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.surfaceDim,
        )
    ) {
        Image(
            painter = painterResource(id = coalesce(lightCone.art, fallback = lightCone.lightCone)),
            contentDescription = stringResource(id = lightCone.description),
            modifier.fillMaxSize()
        )
    }
}

@Composable
fun LightConeListView(forward: (RouteItem) -> Unit, modifier: Modifier = Modifier) {
    var search: String by rememberSaveable { mutableStateOf("") }
    val lightCones = LightCone.entries.filter {
        stringResource(id = it.text).contains(search)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.lightcone),
            fontSize = 7.em,
            fontFamily = SaibaFamily,
        )
        HorizontalDivider(
            thickness = 4.dp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 12.dp)
        )
        SearchBox(
            search = search,
            changeSearch = { search = it },
            openFilter = { forward(LocaleRouteItem(R.string.ui_do_not_touch)) },
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
                .padding(vertical = 12.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 96.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            items(lightCones) {
                LightConeItem(
                    lightCone = it
                )
            }
        }
    }
}