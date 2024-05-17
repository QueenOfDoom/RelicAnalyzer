package edu.shch.hsr.relicanalyzer.ui.view.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Relic

@Composable
fun RelicSetDetailView(relic: Relic) {
    Text(text = stringResource(id = relic.text))
}