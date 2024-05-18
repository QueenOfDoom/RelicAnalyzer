package edu.shch.hsr.relicanalyzer.ui.component.hsr

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.Dialog
import edu.shch.hsr.relicanalyzer.R
import edu.shch.hsr.relicanalyzer.hsr.GenericRelic
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Ornament
import edu.shch.hsr.relicanalyzer.hsr.dynamic.Relic
import edu.shch.hsr.relicanalyzer.ui.theme.SaibaFamily

@Composable
fun EquipmentSetInfoDialogue(equipment: GenericRelic, dismiss: () -> Unit) {
    Dialog(onDismissRequest = dismiss) {
        Card(
            border = BorderStroke(4.dp, MaterialTheme.colorScheme.outline),
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            shape = RoundedCornerShape(64.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(500.dp)
                .requiredHeight(460.dp)
                .offset(y = 40.dp)
                .fillMaxHeight(1f)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .padding(12.dp)
            ) {
                val values = when (equipment) {
                    is Relic -> {
                        linkedMapOf(
                            R.string.ui_set_half to equipment.halfSet,
                            R.string.ui_set_full to equipment.fullSet
                        )
                    }
                    is Ornament -> {
                        linkedMapOf(R.string.ui_set_ornament to equipment.set)
                    }
                    else -> throw IllegalStateException("Unknown Generic Relic found! Neither Relic nor Ornament!")
                }

                for ((label, value) in values) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = label),
                            fontSize = 4.3.em,
                            fontFamily = SaibaFamily,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(id = value),
                            fontSize = 3.8.em,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}