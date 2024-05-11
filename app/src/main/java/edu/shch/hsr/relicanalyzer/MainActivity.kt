package edu.shch.hsr.relicanalyzer

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import edu.shch.hsr.relicanalyzer.hsr.RelicType
import edu.shch.hsr.relicanalyzer.ui.theme.DarkLavender
import edu.shch.hsr.relicanalyzer.ui.theme.OverlayLavender
import edu.shch.hsr.relicanalyzer.ui.theme.PlainAssWhite
import edu.shch.hsr.relicanalyzer.ui.theme.RelicAnalyzerTheme
import edu.shch.hsr.relicanalyzer.ui.theme.WiltingLavender
import kotlin.math.max

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

val saibaFamily = FontFamily(
    Font(R.font.saiba, FontWeight.Normal),
    Font(R.font.saiba_outline, FontWeight.Thin)
)

@Composable
private fun RelicOrnamentChoice(
    onCharacter: () -> Unit,
    onLightCone: () -> Unit,
    onRelicType: (RelicType) -> Unit
) {
    val isRelic: MutableState<Boolean> = remember { mutableStateOf(true) }
    val relic = R.drawable.relic_117_0
    val ornament = R.drawable.ornament_311_0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            /* Text Processing: pad both texts equally */
            val relicNameList = stringResource(id = R.string.relic).split(" ").toMutableList()
            val ornamentNameList = stringResource(id = R.string.ornament).split(" ").toMutableList()
            val rows = max(
                relicNameList.size,
                ornamentNameList.size
            )
            while (relicNameList.size < rows) relicNameList.add("")
            while(ornamentNameList.size < rows) ornamentNameList.add("")
            val primary = (if(isRelic.value) relicNameList else ornamentNameList)
                .joinToString("\n")
            val secondary = (if (isRelic.value) ornamentNameList else relicNameList)
                .joinToString("\n")

            ElevatedButton(
                onClick = { onRelicType(if (isRelic.value) RelicType.Relic else RelicType.PlanarOrnament) },
                border = BorderStroke(
                    4.dp, DarkLavender
                ),
                modifier = Modifier.size(196.dp, 294.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            id = if(isRelic.value) relic else ornament
                        ),
                        contentDescription = primary,
                        modifier = Modifier
                            .padding(top = 40.dp, bottom = 10.dp)
                            .size(128.dp)
                    )
                    Text(
                        text = primary,
                        textAlign = TextAlign.Center,
                        fontFamily = saibaFamily,
                        fontSize = 5.6.em,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                }
            }
            IconButton(
                onClick = { isRelic.value = !isRelic.value },
                modifier = Modifier
                    .offset((-10).dp, 0.dp)
                    .scale(1.25f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.swap_horiz),
                    contentDescription = "Description"
                )
            }
            ElevatedButton(
                onClick = { },
                enabled = false,
                modifier = Modifier.size(100.dp, 150.dp),
                border = BorderStroke(
                    4.dp, DarkLavender
                ),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.alignByBaseline()
                ) {
                    Image(
                        painter = painterResource(
                            id = if(isRelic.value) ornament else relic
                        ),
                        contentDescription = secondary,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 2.dp)
                            .size(92.dp)
                    )
                    Text(
                        text = secondary,
                        textAlign = TextAlign.Center,
                        fontFamily = saibaFamily,
                        fontSize = 2.em,
                        lineHeight = 1.em,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }
            }
        }
        Column {
            ElevatedButton(
                onClick = { onCharacter() },
                border = BorderStroke(
                    4.dp, DarkLavender
                ),
                modifier = Modifier.fillMaxWidth(fraction = 0.8f)
            ) {
                Text(
                    text = stringResource(id = R.string.character),
                    fontSize = 7.em,
                    fontFamily = saibaFamily,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            ElevatedButton(
                onClick = { onLightCone() },
                border = BorderStroke(
                    4.dp, DarkLavender
                ),
                modifier = Modifier.fillMaxWidth(fraction = 0.8f)
            ) {
                Text(
                    text = stringResource(id = R.string.lightcone),
                    fontSize = 7.em,
                    fontFamily = saibaFamily,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelicAnalyzer(modifier: Modifier = Modifier, dispatcher: OnBackPressedDispatcher) {
    var relicType: RelicType? by rememberSaveable { mutableStateOf(null) }
    var showMaintenanceDialogue: Boolean by rememberSaveable { mutableStateOf(false) }
    var isInCharacter: Boolean by rememberSaveable { mutableStateOf(false) }
    var isInLightCones: Boolean by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    if (showMaintenanceDialogue) {
        BasicAlertDialog(
            onDismissRequest = { showMaintenanceDialogue = false },
            content = {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.5f)
                        .background(OverlayLavender, RoundedCornerShape(64.dp))
                        .border(4.dp, WiltingLavender, shape = RoundedCornerShape(64.dp))
                        .padding(top = 40.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.ui_do_not_touch),
                        fontSize = 7.em,
                        fontWeight = FontWeight.Bold,
                        color = PlainAssWhite
                    )
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context).data(data = R.drawable.herta_kuru)
                                .apply(block = {
                                    size(Size.ORIGINAL)
                                }).build(),
                            imageLoader = imageLoader
                        ),
                        contentDescription = null,
                        modifier = modifier.fillMaxWidth(),
                    )
                }
            })
    }

    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        if (isInCharacter) {
            CharacterView(dispatcher)  { isInCharacter = false }
        } else if(isInLightCones) {
            isInLightCones = false
            showMaintenanceDialogue = true
        } else {
            when (relicType) {
                RelicType.Relic -> {
                    RelicView(
                        dispatcher,
                        { showMaintenanceDialogue = true },
                        Modifier.padding(top = 60.dp)
                    ) { relicType = null }
                }
                RelicType.PlanarOrnament -> {
                    OrnamentView(
                        dispatcher,
                        { showMaintenanceDialogue = true },
                        Modifier.padding(top = 60.dp)
                    ) { relicType = null }
                }
                null -> RelicOrnamentChoice(
                    { isInCharacter = true },
                    { isInLightCones = true },
                    { type -> relicType = type }
                )
            }
        }
    }
}
