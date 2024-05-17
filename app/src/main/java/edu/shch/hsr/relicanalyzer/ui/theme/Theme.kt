package edu.shch.hsr.relicanalyzer.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = ShadowyLavender,
    onBackground = PlainAssWhite,

    primary = PaleLavender,
    secondary = BrightLavender,
    tertiary = NormalLavender,

    surface = DimLavender,
    onSurface = PaleLavender,
    surfaceDim = DarkLavender,
    surfaceContainerLow = DimLavender,

    outline = WiltingLavender,
    outlineVariant = WiltingLavender
)

private val LightColorScheme = lightColorScheme(
    background = SoftLilac,
    onBackground = AlmostBlack,

    primary = DeepLilac,
    secondary = LightLilac,
    tertiary = FaintLilac,

    surface = LightSurfaceLilac,
    surfaceDim = LightSurfaceLilac,
    surfaceContainerLow = LightSurfaceLilac,

    outline = SubtleLilac,
    outlineVariant = SubtleLilac
)

@Composable
fun RelicAnalyzerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}