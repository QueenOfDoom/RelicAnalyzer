package edu.shch.hsr.relicanalyzer.ui.view.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Ornament

@Composable
fun OrnamentSetDetailView(ornament: Ornament) {
    Text(text = stringResource(id = ornament.text))
}