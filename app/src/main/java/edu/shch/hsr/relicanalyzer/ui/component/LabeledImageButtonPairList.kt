package edu.shch.hsr.relicanalyzer.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T, Y> LabeledImageButtonPairList(
    list: List<T>,
    rowExtractor: (T) -> Array<Y>,
    imageExtractor: (Y) -> Int,
    textExtractor: (Y) -> Int,
    onClick: (Y) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                .fillMaxWidth()
        ) {
            itemsIndexed(list) { i, item ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (set in rowExtractor(item)) {
                        LabeledImageButton(
                            img = imageExtractor(set),
                            label = textExtractor(set),
                            onClick = { onClick(set) },
                            modifier = Modifier.width(160.dp).padding(4.dp),
                            buttonModifier = Modifier.size(140.dp)
                        )
                    }
                }
                if (i != list.size - 1) {
                    HorizontalDivider(
                        thickness = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}