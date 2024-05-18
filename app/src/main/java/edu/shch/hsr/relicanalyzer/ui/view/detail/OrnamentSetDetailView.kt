package edu.shch.hsr.relicanalyzer.ui.view.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Ornament

@Composable
fun OrnamentSetDetailView(ornament: Ornament, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = stringResource(id = ornament.text))
    }
}