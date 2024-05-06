package edu.shch.hsr.relicanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import edu.shch.hsr.relicanalyzer.hsr.Ornament
import edu.shch.hsr.relicanalyzer.hsr.OrnamentSlot
import edu.shch.hsr.relicanalyzer.hsr.Relic
import edu.shch.hsr.relicanalyzer.hsr.RelicSlot
import edu.shch.hsr.relicanalyzer.hsr.RelicType
import edu.shch.hsr.relicanalyzer.ui.theme.RelicAnalyzerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RelicAnalyzerTheme {
                RelicAnalyzer(modifier = Modifier.fillMaxSize(), onBackPressedDispatcher)
            }
        }
    }
}

@Composable
fun MediaButton(
    @StringRes vararg text: Int,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    fontSize: TextUnit = 6.em,
    separator: String = " | ",
    onClick: () -> Unit
) {
    ElevatedButton(onClick) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
        ) {
            @Suppress("SimplifiableCallChain")
            Image(
                painter = painterResource(id = image),
                contentDescription = text.map { stringResource(id = it) }.joinToString(separator),
                modifier = imageModifier
            )
            @Suppress("SimplifiableCallChain")
            Text(
                text = text.map { stringResource(id = it) }.joinToString(separator),
                fontWeight = FontWeight.ExtraBold,
                fontSize = fontSize,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun RelicOrnamentChoice(onClick: (RelicType) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(22.dp)
        val imageModifier = Modifier
            .scale(1.5f)
            .padding(24.dp)
        MediaButton(
            text = intArrayOf(R.string.relic),
            image = R.drawable.relic_105_0,
            buttonModifier,
            imageModifier
        ) { onClick(RelicType.Relic) }
        MediaButton(
            text = intArrayOf(R.string.ornament),
            image = R.drawable.ornament_301_0,
            buttonModifier,
            imageModifier
        ) { onClick(RelicType.PlanarOrnament) }
    }
}

@Composable
private fun RelicOrnamentSetChoice(
    relicType: RelicType?,
    chooseRelic: (Relic) -> Unit,
    chooseOrnament: (Ornament) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 112.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxHeight()
    ) {
        when (relicType!!) {
            RelicType.Relic -> {
                items(Relic.entries) {
                    ElevatedButton(onClick = { chooseRelic(it) }) {
                        Image(
                            painter = painterResource(id = it.head),
                            contentDescription = stringResource(id = it.text),
                            modifier = Modifier.size(96.dp, 96.dp)
                        )
                    }
                }
            }

            RelicType.PlanarOrnament -> {
                items(Ornament.entries) {
                    ElevatedButton(onClick = { chooseOrnament(it) }) {
                        Image(
                            painter = painterResource(id = it.planarSphere),
                            contentDescription = stringResource(id = it.text),
                            modifier = Modifier.size(96.dp, 96.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RelicSlotSelector(
    relicSet: Relic,
    onClick: (RelicSlot) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val fontSize = 3.em
        val buttonModifier = Modifier
            .fillMaxWidth()
        val imageModifier = Modifier
            .size(96.dp)
            .padding(12.dp)
        MediaButton(
            text = intArrayOf(relicSet.text, RelicSlot.Head.text),
            image = relicSet.head,
            buttonModifier,
            imageModifier,
            fontSize
        ) { onClick(RelicSlot.Head) }
        MediaButton(
            text = intArrayOf(relicSet.text, RelicSlot.Hands.text),
            image = relicSet.hands,
            buttonModifier,
            imageModifier,
            fontSize
        ) { onClick(RelicSlot.Hands) }
        MediaButton(
            text = intArrayOf(relicSet.text, RelicSlot.Body.text),
            image = relicSet.body,
            buttonModifier,
            imageModifier,
            fontSize
        ) { onClick(RelicSlot.Body) }
        MediaButton(
            text = intArrayOf(relicSet.text, RelicSlot.Feet.text),
            image = relicSet.feet,
            buttonModifier,
            imageModifier,
            fontSize
        ) { onClick(RelicSlot.Feet) }
    }
}

@Composable
private fun OrnamentSlotSelector(
    ornamentSet: Ornament,
    onClick: (OrnamentSlot) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val fontSize = 5.em
        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 22.dp, top = 12.dp, start = 26.dp, end = 16.dp)
        val imageModifier = Modifier
            .size(160.dp)
            .padding(24.dp)
        MediaButton(
            text = intArrayOf(ornamentSet.text, OrnamentSlot.PlanarSphere.text),
            image = ornamentSet.planarSphere,
            buttonModifier,
            imageModifier,
            fontSize,
            separator = "\n"
        ) { onClick(OrnamentSlot.PlanarSphere) }
        MediaButton(
            text = intArrayOf(ornamentSet.text, OrnamentSlot.LinkRope.text),
            image = ornamentSet.linkRope,
            buttonModifier,
            imageModifier,
            fontSize,
            separator = "\n"
        ) { onClick(OrnamentSlot.LinkRope) }
    }
}

@Composable
fun RelicAnalyzer(modifier: Modifier = Modifier, dispatcher: OnBackPressedDispatcher) {
    var relicType: RelicType? by rememberSaveable { mutableStateOf(null) }
    var relicSet: Relic? by rememberSaveable { mutableStateOf(null) }
    var ornamentSet: Ornament? by rememberSaveable { mutableStateOf(null) }
    var relicSlot: RelicSlot? by rememberSaveable { mutableStateOf(null) }
    var ornamentSlot: OrnamentSlot? by rememberSaveable { mutableStateOf(null) }
    var mainStat: String? by rememberSaveable { mutableStateOf(null) }
    // TODO: Make Stats into an Enum with Localizations and Drop-Downs!

    dispatcher.addCallback {
        if (relicSlot != null || ornamentSlot != null) {
            relicSlot = null
            ornamentSlot = null
        } else if (ornamentSet != null || relicSet != null) {
            ornamentSet = null
            relicSet = null
        } else if (relicType != null) {
            relicType = null
        }
    }

    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        if (relicType == null) {
            RelicOrnamentChoice { type -> relicType = type }
        } else if (relicSet == null && ornamentSet == null) {
            RelicOrnamentSetChoice(relicType, { relicSet = it }, { ornamentSet = it })
        } else if (relicSlot == null && ornamentSlot == null) {
            when (relicType!!) {
                RelicType.Relic -> {
                    RelicSlotSelector(relicSet!!) { relicSlot = it }
                }
                RelicType.PlanarOrnament -> {
                    OrnamentSlotSelector(ornamentSet!!) { ornamentSlot = it }
                }
            }
        } else if (mainStat == null) {

        }
    }
}
