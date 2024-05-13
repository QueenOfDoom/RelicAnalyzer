package edu.shch.hsr.relicanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import edu.shch.hsr.relicanalyzer.ui.theme.RelicAnalyzerTheme
import edu.shch.hsr.relicanalyzer.ui.view.CharacterEntryView
import edu.shch.hsr.relicanalyzer.ui.view.ChooseSubject
import edu.shch.hsr.relicanalyzer.ui.view.LightConeEntryView
import edu.shch.hsr.relicanalyzer.ui.view.OrnamentEntryView
import edu.shch.hsr.relicanalyzer.ui.view.RelicEntryView
import edu.shch.hsr.relicanalyzer.util.LocaleRouteItem
import edu.shch.hsr.relicanalyzer.util.RouteItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RelicAnalyzerTheme(dynamicColor = false) {
                RelicAnalyzer(
                    modifier = Modifier.fillMaxSize(),
                    onBackPressedDispatcher
                )
            }
        }
    }
}

@Composable
fun RelicAnalyzer(
    modifier: Modifier = Modifier,
    dispatcher: OnBackPressedDispatcher
) {
    /* history-logic */
    val path = remember { mutableStateListOf<RouteItem>() }

    dispatcher.addCallback { path.removeLast() }

    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        if (path.size == 0) {
            ChooseSubject(
                { path.add(it) },
                modifier = modifier
            )
        } else {
            // TODO: Build Multi-Level Router Logic
            // For now, let's assume path.size == 1
            val item = path[0] as LocaleRouteItem
            when (item.id) {
                R.string.relic -> RelicEntryView()
                R.string.ornament -> OrnamentEntryView()
                R.string.character -> CharacterEntryView()
                R.string.lightcone -> LightConeEntryView()
                else -> throw IllegalStateException("Came across illegal transition.")
            }
        }
    }
}