package com.example.ft_hangouts.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.compose.greenPrimaryLight
import com.example.compose.greenbackgroundDark
import com.example.compose.greenbackgroundLight
import com.example.compose.greenerrorContainerDark
import com.example.compose.greenerrorContainerLight
import com.example.compose.greenerrorDark
import com.example.compose.greenerrorLight
import com.example.compose.greeninverseOnSurfaceDark
import com.example.compose.greeninverseOnSurfaceLight
import com.example.compose.greeninversePrimaryDark
import com.example.compose.greeninversePrimaryLight
import com.example.compose.greeninverseSurfaceDark
import com.example.compose.greeninverseSurfaceLight
import com.example.compose.greenonBackgroundDark
import com.example.compose.greenonBackgroundLight
import com.example.compose.greenonErrorContainerDark
import com.example.compose.greenonErrorContainerLight
import com.example.compose.greenonErrorDark
import com.example.compose.greenonErrorLight
import com.example.compose.greenonPrimaryContainerDark
import com.example.compose.greenonPrimaryContainerLight
import com.example.compose.greenonPrimaryDark
import com.example.compose.greenonPrimaryLight
import com.example.compose.greenonSecondaryContainerDark
import com.example.compose.greenonSecondaryContainerLight
import com.example.compose.greenonSecondaryDark
import com.example.compose.greenonSecondaryLight
import com.example.compose.greenonSurfaceDark
import com.example.compose.greenonSurfaceLight
import com.example.compose.greenonSurfaceVariantDark
import com.example.compose.greenonSurfaceVariantLight
import com.example.compose.greenonTertiaryContainerDark
import com.example.compose.greenonTertiaryContainerLight
import com.example.compose.greenonTertiaryDark
import com.example.compose.greenonTertiaryLight
import com.example.compose.greenoutlineDark
import com.example.compose.greenoutlineLight
import com.example.compose.greenoutlineVariantDark
import com.example.compose.greenoutlineVariantLight
import com.example.compose.greenprimaryContainerDark
import com.example.compose.greenprimaryContainerLight
import com.example.compose.greenprimaryDark
import com.example.compose.greenscrimDark
import com.example.compose.greenscrimLight
import com.example.compose.greensecondaryContainerDark
import com.example.compose.greensecondaryContainerLight
import com.example.compose.greensecondaryDark
import com.example.compose.greensecondaryLight
import com.example.compose.greensurfaceBrightDark
import com.example.compose.greensurfaceBrightLight
import com.example.compose.greensurfaceContainerDark
import com.example.compose.greensurfaceContainerHighDark
import com.example.compose.greensurfaceContainerHighLight
import com.example.compose.greensurfaceContainerHighestDark
import com.example.compose.greensurfaceContainerHighestLight
import com.example.compose.greensurfaceContainerLight
import com.example.compose.greensurfaceContainerLowDark
import com.example.compose.greensurfaceContainerLowLight
import com.example.compose.greensurfaceContainerLowestDark
import com.example.compose.greensurfaceContainerLowestLight
import com.example.compose.greensurfaceDark
import com.example.compose.greensurfaceDimDark
import com.example.compose.greensurfaceDimLight
import com.example.compose.greensurfaceLight
import com.example.compose.greensurfaceVariantDark
import com.example.compose.greensurfaceVariantLight
import com.example.compose.greentertiaryContainerDark
import com.example.compose.greentertiaryContainerLight
import com.example.compose.greentertiaryDark
import com.example.compose.greentertiaryLight
import com.example.compose.pinkPrimaryLight
import com.example.compose.pinkbackgroundDark
import com.example.compose.pinkbackgroundLight
import com.example.compose.pinkerrorContainerDark
import com.example.compose.pinkerrorContainerLight
import com.example.compose.pinkerrorDark
import com.example.compose.pinkerrorLight
import com.example.compose.pinkinverseOnSurfaceDark
import com.example.compose.pinkinverseOnSurfaceLight
import com.example.compose.pinkinversePrimaryDark
import com.example.compose.pinkinversePrimaryLight
import com.example.compose.pinkinverseSurfaceDark
import com.example.compose.pinkinverseSurfaceLight
import com.example.compose.pinkonBackgroundDark
import com.example.compose.pinkonBackgroundLight
import com.example.compose.pinkonErrorContainerDark
import com.example.compose.pinkonErrorContainerLight
import com.example.compose.pinkonErrorDark
import com.example.compose.pinkonErrorLight
import com.example.compose.pinkonPrimaryContainerDark
import com.example.compose.pinkonPrimaryContainerLight
import com.example.compose.pinkonPrimaryDark
import com.example.compose.pinkonPrimaryLight
import com.example.compose.pinkonSecondaryContainerDark
import com.example.compose.pinkonSecondaryContainerLight
import com.example.compose.pinkonSecondaryDark
import com.example.compose.pinkonSecondaryLight
import com.example.compose.pinkonSurfaceDark
import com.example.compose.pinkonSurfaceLight
import com.example.compose.pinkonSurfaceVariantDark
import com.example.compose.pinkonSurfaceVariantLight
import com.example.compose.pinkonTertiaryContainerDark
import com.example.compose.pinkonTertiaryContainerLight
import com.example.compose.pinkonTertiaryDark
import com.example.compose.pinkonTertiaryLight
import com.example.compose.pinkoutlineDark
import com.example.compose.pinkoutlineLight
import com.example.compose.pinkoutlineVariantDark
import com.example.compose.pinkoutlineVariantLight
import com.example.compose.pinkprimaryContainerDark
import com.example.compose.pinkprimaryContainerLight
import com.example.compose.pinkprimaryDark
import com.example.compose.pinkscrimDark
import com.example.compose.pinkscrimLight
import com.example.compose.pinksecondaryContainerDark
import com.example.compose.pinksecondaryContainerLight
import com.example.compose.pinksecondaryDark
import com.example.compose.pinksecondaryLight
import com.example.compose.pinksurfaceBrightDark
import com.example.compose.pinksurfaceBrightLight
import com.example.compose.pinksurfaceContainerDark
import com.example.compose.pinksurfaceContainerHighDark
import com.example.compose.pinksurfaceContainerHighLight
import com.example.compose.pinksurfaceContainerHighestDark
import com.example.compose.pinksurfaceContainerHighestLight
import com.example.compose.pinksurfaceContainerLight
import com.example.compose.pinksurfaceContainerLowDark
import com.example.compose.pinksurfaceContainerLowLight
import com.example.compose.pinksurfaceContainerLowestDark
import com.example.compose.pinksurfaceContainerLowestLight
import com.example.compose.pinksurfaceDark
import com.example.compose.pinksurfaceDimDark
import com.example.compose.pinksurfaceDimLight
import com.example.compose.pinksurfaceLight
import com.example.compose.pinksurfaceVariantDark
import com.example.compose.pinksurfaceVariantLight
import com.example.compose.pinktertiaryContainerDark
import com.example.compose.pinktertiaryContainerLight
import com.example.compose.pinktertiaryDark
import com.example.compose.pinktertiaryLight
import com.example.compose.purplePrimaryLight
import com.example.compose.purplebackgroundDark
import com.example.compose.purplebackgroundLight
import com.example.compose.purpleerrorContainerDark
import com.example.compose.purpleerrorContainerLight
import com.example.compose.purpleerrorDark
import com.example.compose.purpleerrorLight
import com.example.compose.purpleinverseOnSurfaceDark
import com.example.compose.purpleinverseOnSurfaceLight
import com.example.compose.purpleinversePrimaryDark
import com.example.compose.purpleinversePrimaryLight
import com.example.compose.purpleinverseSurfaceDark
import com.example.compose.purpleinverseSurfaceLight
import com.example.compose.purpleonBackgroundDark
import com.example.compose.purpleonBackgroundLight
import com.example.compose.purpleonErrorContainerDark
import com.example.compose.purpleonErrorContainerLight
import com.example.compose.purpleonErrorDark
import com.example.compose.purpleonErrorLight
import com.example.compose.purpleonPrimaryContainerDark
import com.example.compose.purpleonPrimaryContainerLight
import com.example.compose.purpleonPrimaryDark
import com.example.compose.purpleonPrimaryLight
import com.example.compose.purpleonSecondaryContainerDark
import com.example.compose.purpleonSecondaryContainerLight
import com.example.compose.purpleonSecondaryDark
import com.example.compose.purpleonSecondaryLight
import com.example.compose.purpleonSurfaceDark
import com.example.compose.purpleonSurfaceLight
import com.example.compose.purpleonSurfaceVariantDark
import com.example.compose.purpleonSurfaceVariantLight
import com.example.compose.purpleonTertiaryContainerDark
import com.example.compose.purpleonTertiaryContainerLight
import com.example.compose.purpleonTertiaryDark
import com.example.compose.purpleonTertiaryLight
import com.example.compose.purpleoutlineDark
import com.example.compose.purpleoutlineLight
import com.example.compose.purpleoutlineVariantDark
import com.example.compose.purpleoutlineVariantLight
import com.example.compose.purpleprimaryContainerDark
import com.example.compose.purpleprimaryContainerLight
import com.example.compose.purpleprimaryDark
import com.example.compose.purplescrimDark
import com.example.compose.purplescrimLight
import com.example.compose.purplesecondaryContainerDark
import com.example.compose.purplesecondaryContainerLight
import com.example.compose.purplesecondaryDark
import com.example.compose.purplesecondaryLight
import com.example.compose.purplesurfaceBrightDark
import com.example.compose.purplesurfaceBrightLight
import com.example.compose.purplesurfaceContainerDark
import com.example.compose.purplesurfaceContainerHighDark
import com.example.compose.purplesurfaceContainerHighLight
import com.example.compose.purplesurfaceContainerHighestDark
import com.example.compose.purplesurfaceContainerHighestLight
import com.example.compose.purplesurfaceContainerLight
import com.example.compose.purplesurfaceContainerLowDark
import com.example.compose.purplesurfaceContainerLowLight
import com.example.compose.purplesurfaceContainerLowestDark
import com.example.compose.purplesurfaceContainerLowestLight
import com.example.compose.purplesurfaceDark
import com.example.compose.purplesurfaceDimDark
import com.example.compose.purplesurfaceDimLight
import com.example.compose.purplesurfaceLight
import com.example.compose.purplesurfaceVariantDark
import com.example.compose.purplesurfaceVariantLight
import com.example.compose.purpletertiaryContainerDark
import com.example.compose.purpletertiaryContainerLight
import com.example.compose.purpletertiaryDark
import com.example.compose.purpletertiaryLight

private val blueLightScheme = lightColorScheme(
    primary = bluePrimaryLight,
    onPrimary = blueonPrimaryLight,
    primaryContainer = blueprimaryContainerLight,
    onPrimaryContainer = blueonPrimaryContainerLight,
    secondary = bluesecondaryLight,
    onSecondary = blueonSecondaryLight,
    secondaryContainer = bluesecondaryContainerLight,
    onSecondaryContainer = blueonSecondaryContainerLight,
    tertiary = bluetertiaryLight,
    onTertiary = blueonTertiaryLight,
    tertiaryContainer = bluetertiaryContainerLight,
    onTertiaryContainer = blueonTertiaryContainerLight,
    error = blueerrorLight,
    onError = blueonErrorLight,
    errorContainer = blueerrorContainerLight,
    onErrorContainer = blueonErrorContainerLight,
    background = bluebackgroundLight,
    onBackground = blueonBackgroundLight,
    surface = bluesurfaceLight,
    onSurface = blueonSurfaceLight,
    surfaceVariant = bluesurfaceVariantLight,
    onSurfaceVariant = blueonSurfaceVariantLight,
    outline = blueoutlineLight,
    outlineVariant = blueoutlineVariantLight,
    scrim = bluescrimLight,
    inverseSurface = blueinverseSurfaceLight,
    inverseOnSurface = blueinverseOnSurfaceLight,
    inversePrimary = blueinversePrimaryLight,
    surfaceDim = bluesurfaceDimLight,
    surfaceBright = bluesurfaceBrightLight,
    surfaceContainerLowest = bluesurfaceContainerLowestLight,
    surfaceContainerLow = bluesurfaceContainerLowLight,
    surfaceContainer = bluesurfaceContainerLight,
    surfaceContainerHigh = bluesurfaceContainerHighLight,
    surfaceContainerHighest = bluesurfaceContainerHighestLight,
)

private val blueDarkScheme = darkColorScheme(
    primary = blueprimaryDark,
    onPrimary = blueonPrimaryDark,
    primaryContainer = blueprimaryContainerDark,
    onPrimaryContainer = blueonPrimaryContainerDark,
    secondary = bluesecondaryDark,
    onSecondary = blueonSecondaryDark,
    secondaryContainer = bluesecondaryContainerDark,
    onSecondaryContainer = blueonSecondaryContainerDark,
    tertiary = bluetertiaryDark,
    onTertiary = blueonTertiaryDark,
    tertiaryContainer = bluetertiaryContainerDark,
    onTertiaryContainer = blueonTertiaryContainerDark,
    error = blueerrorDark,
    onError = blueonErrorDark,
    errorContainer = blueerrorContainerDark,
    onErrorContainer = blueonErrorContainerDark,
    background = bluebackgroundDark,
    onBackground = blueonBackgroundDark,
    surface = bluesurfaceDark,
    onSurface = blueonSurfaceDark,
    surfaceVariant = bluesurfaceVariantDark,
    onSurfaceVariant = blueonSurfaceVariantDark,
    outline = blueoutlineDark,
    outlineVariant = blueoutlineVariantDark,
    scrim = bluescrimDark,
    inverseSurface = blueinverseSurfaceDark,
    inverseOnSurface = blueinverseOnSurfaceDark,
    inversePrimary = blueinversePrimaryDark,
    surfaceDim = bluesurfaceDimDark,
    surfaceBright = bluesurfaceBrightDark,
    surfaceContainerLowest = bluesurfaceContainerLowestDark,
    surfaceContainerLow = bluesurfaceContainerLowDark,
    surfaceContainer = bluesurfaceContainerDark,
    surfaceContainerHigh = bluesurfaceContainerHighDark,
    surfaceContainerHighest = bluesurfaceContainerHighestDark,
)

private val greenLightScheme = lightColorScheme(
    primary = greenPrimaryLight,
    onPrimary = greenonPrimaryLight,
    primaryContainer = greenprimaryContainerLight,
    onPrimaryContainer = greenonPrimaryContainerLight,
    secondary = greensecondaryLight,
    onSecondary = greenonSecondaryLight,
    secondaryContainer = greensecondaryContainerLight,
    onSecondaryContainer = greenonSecondaryContainerLight,
    tertiary = greentertiaryLight,
    onTertiary = greenonTertiaryLight,
    tertiaryContainer = greentertiaryContainerLight,
    onTertiaryContainer = greenonTertiaryContainerLight,
    error = greenerrorLight,
    onError = greenonErrorLight,
    errorContainer = greenerrorContainerLight,
    onErrorContainer = greenonErrorContainerLight,
    background = greenbackgroundLight,
    onBackground = greenonBackgroundLight,
    surface = greensurfaceLight,
    onSurface = greenonSurfaceLight,
    surfaceVariant = greensurfaceVariantLight,
    onSurfaceVariant = greenonSurfaceVariantLight,
    outline = greenoutlineLight,
    outlineVariant = greenoutlineVariantLight,
    scrim = greenscrimLight,
    inverseSurface = greeninverseSurfaceLight,
    inverseOnSurface = greeninverseOnSurfaceLight,
    inversePrimary = greeninversePrimaryLight,
    surfaceDim = greensurfaceDimLight,
    surfaceBright = greensurfaceBrightLight,
    surfaceContainerLowest = greensurfaceContainerLowestLight,
    surfaceContainerLow = greensurfaceContainerLowLight,
    surfaceContainer = greensurfaceContainerLight,
    surfaceContainerHigh = greensurfaceContainerHighLight,
    surfaceContainerHighest = greensurfaceContainerHighestLight,
)

private val greenDarkScheme = darkColorScheme(
    primary = greenprimaryDark,
    onPrimary = greenonPrimaryDark,
    primaryContainer = greenprimaryContainerDark,
    onPrimaryContainer = greenonPrimaryContainerDark,
    secondary = greensecondaryDark,
    onSecondary = greenonSecondaryDark,
    secondaryContainer = greensecondaryContainerDark,
    onSecondaryContainer = greenonSecondaryContainerDark,
    tertiary = greentertiaryDark,
    onTertiary = greenonTertiaryDark,
    tertiaryContainer = greentertiaryContainerDark,
    onTertiaryContainer = greenonTertiaryContainerDark,
    error = greenerrorDark,
    onError = greenonErrorDark,
    errorContainer = greenerrorContainerDark,
    onErrorContainer = greenonErrorContainerDark,
    background = greenbackgroundDark,
    onBackground = greenonBackgroundDark,
    surface = greensurfaceDark,
    onSurface = greenonSurfaceDark,
    surfaceVariant = greensurfaceVariantDark,
    onSurfaceVariant = greenonSurfaceVariantDark,
    outline = greenoutlineDark,
    outlineVariant = greenoutlineVariantDark,
    scrim = greenscrimDark,
    inverseSurface = greeninverseSurfaceDark,
    inverseOnSurface = greeninverseOnSurfaceDark,
    inversePrimary = greeninversePrimaryDark,
    surfaceDim = greensurfaceDimDark,
    surfaceBright = greensurfaceBrightDark,
    surfaceContainerLowest = greensurfaceContainerLowestDark,
    surfaceContainerLow = greensurfaceContainerLowDark,
    surfaceContainer = greensurfaceContainerDark,
    surfaceContainerHigh = greensurfaceContainerHighDark,
    surfaceContainerHighest = greensurfaceContainerHighestDark,
)

private val pinkLightScheme = lightColorScheme(
    primary = pinkPrimaryLight,
    onPrimary = pinkonPrimaryLight,
    primaryContainer = pinkprimaryContainerLight,
    onPrimaryContainer = pinkonPrimaryContainerLight,
    secondary = pinksecondaryLight,
    onSecondary = pinkonSecondaryLight,
    secondaryContainer = pinksecondaryContainerLight,
    onSecondaryContainer = pinkonSecondaryContainerLight,
    tertiary = pinktertiaryLight,
    onTertiary = pinkonTertiaryLight,
    tertiaryContainer = pinktertiaryContainerLight,
    onTertiaryContainer = pinkonTertiaryContainerLight,
    error = pinkerrorLight,
    onError = pinkonErrorLight,
    errorContainer = pinkerrorContainerLight,
    onErrorContainer = pinkonErrorContainerLight,
    background = pinkbackgroundLight,
    onBackground = pinkonBackgroundLight,
    surface = pinksurfaceLight,
    onSurface = pinkonSurfaceLight,
    surfaceVariant = pinksurfaceVariantLight,
    onSurfaceVariant = pinkonSurfaceVariantLight,
    outline = pinkoutlineLight,
    outlineVariant = pinkoutlineVariantLight,
    scrim = pinkscrimLight,
    inverseSurface = pinkinverseSurfaceLight,
    inverseOnSurface = pinkinverseOnSurfaceLight,
    inversePrimary = pinkinversePrimaryLight,
    surfaceDim = pinksurfaceDimLight,
    surfaceBright = pinksurfaceBrightLight,
    surfaceContainerLowest = pinksurfaceContainerLowestLight,
    surfaceContainerLow = pinksurfaceContainerLowLight,
    surfaceContainer = pinksurfaceContainerLight,
    surfaceContainerHigh = pinksurfaceContainerHighLight,
    surfaceContainerHighest = pinksurfaceContainerHighestLight,
)

private val pinkDarkScheme = darkColorScheme(
    primary = pinkprimaryDark,
    onPrimary = pinkonPrimaryDark,
    primaryContainer = pinkprimaryContainerDark,
    onPrimaryContainer = pinkonPrimaryContainerDark,
    secondary = pinksecondaryDark,
    onSecondary = pinkonSecondaryDark,
    secondaryContainer = pinksecondaryContainerDark,
    onSecondaryContainer = pinkonSecondaryContainerDark,
    tertiary = pinktertiaryDark,
    onTertiary = pinkonTertiaryDark,
    tertiaryContainer = pinktertiaryContainerDark,
    onTertiaryContainer = pinkonTertiaryContainerDark,
    error = pinkerrorDark,
    onError = pinkonErrorDark,
    errorContainer = pinkerrorContainerDark,
    onErrorContainer = pinkonErrorContainerDark,
    background = pinkbackgroundDark,
    onBackground = pinkonBackgroundDark,
    surface = pinksurfaceDark,
    onSurface = pinkonSurfaceDark,
    surfaceVariant = pinksurfaceVariantDark,
    onSurfaceVariant = pinkonSurfaceVariantDark,
    outline = pinkoutlineDark,
    outlineVariant = pinkoutlineVariantDark,
    scrim = pinkscrimDark,
    inverseSurface = pinkinverseSurfaceDark,
    inverseOnSurface = pinkinverseOnSurfaceDark,
    inversePrimary = pinkinversePrimaryDark,
    surfaceDim = pinksurfaceDimDark,
    surfaceBright = pinksurfaceBrightDark,
    surfaceContainerLowest = pinksurfaceContainerLowestDark,
    surfaceContainerLow = pinksurfaceContainerLowDark,
    surfaceContainer = pinksurfaceContainerDark,
    surfaceContainerHigh = pinksurfaceContainerHighDark,
    surfaceContainerHighest = pinksurfaceContainerHighestDark,
)


private val purpleLightScheme = lightColorScheme(
    primary = purplePrimaryLight,
    onPrimary = purpleonPrimaryLight,
    primaryContainer = purpleprimaryContainerLight,
    onPrimaryContainer = purpleonPrimaryContainerLight,
    secondary = purplesecondaryLight,
    onSecondary = purpleonSecondaryLight,
    secondaryContainer = purplesecondaryContainerLight,
    onSecondaryContainer = purpleonSecondaryContainerLight,
    tertiary = purpletertiaryLight,
    onTertiary = purpleonTertiaryLight,
    tertiaryContainer = purpletertiaryContainerLight,
    onTertiaryContainer = purpleonTertiaryContainerLight,
    error = purpleerrorLight,
    onError = purpleonErrorLight,
    errorContainer = purpleerrorContainerLight,
    onErrorContainer = purpleonErrorContainerLight,
    background = purplebackgroundLight,
    onBackground = purpleonBackgroundLight,
    surface = purplesurfaceLight,
    onSurface = purpleonSurfaceLight,
    surfaceVariant = purplesurfaceVariantLight,
    onSurfaceVariant = purpleonSurfaceVariantLight,
    outline = purpleoutlineLight,
    outlineVariant = purpleoutlineVariantLight,
    scrim = purplescrimLight,
    inverseSurface = purpleinverseSurfaceLight,
    inverseOnSurface = purpleinverseOnSurfaceLight,
    inversePrimary = purpleinversePrimaryLight,
    surfaceDim = purplesurfaceDimLight,
    surfaceBright = purplesurfaceBrightLight,
    surfaceContainerLowest = purplesurfaceContainerLowestLight,
    surfaceContainerLow = purplesurfaceContainerLowLight,
    surfaceContainer = purplesurfaceContainerLight,
    surfaceContainerHigh = purplesurfaceContainerHighLight,
    surfaceContainerHighest = purplesurfaceContainerHighestLight,
)

private val purpleDarkScheme = darkColorScheme(
    primary = purpleprimaryDark,
    onPrimary = purpleonPrimaryDark,
    primaryContainer = purpleprimaryContainerDark,
    onPrimaryContainer = purpleonPrimaryContainerDark,
    secondary = purplesecondaryDark,
    onSecondary = purpleonSecondaryDark,
    secondaryContainer = purplesecondaryContainerDark,
    onSecondaryContainer = purpleonSecondaryContainerDark,
    tertiary = purpletertiaryDark,
    onTertiary = purpleonTertiaryDark,
    tertiaryContainer = purpletertiaryContainerDark,
    onTertiaryContainer = purpleonTertiaryContainerDark,
    error = purpleerrorDark,
    onError = purpleonErrorDark,
    errorContainer = purpleerrorContainerDark,
    onErrorContainer = purpleonErrorContainerDark,
    background = purplebackgroundDark,
    onBackground = purpleonBackgroundDark,
    surface = purplesurfaceDark,
    onSurface = purpleonSurfaceDark,
    surfaceVariant = purplesurfaceVariantDark,
    onSurfaceVariant = purpleonSurfaceVariantDark,
    outline = purpleoutlineDark,
    outlineVariant = purpleoutlineVariantDark,
    scrim = purplescrimDark,
    inverseSurface = purpleinverseSurfaceDark,
    inverseOnSurface = purpleinverseOnSurfaceDark,
    inversePrimary = purpleinversePrimaryDark,
    surfaceDim = purplesurfaceDimDark,
    surfaceBright = purplesurfaceBrightDark,
    surfaceContainerLowest = purplesurfaceContainerLowestDark,
    surfaceContainerLow = purplesurfaceContainerLowDark,
    surfaceContainer = purplesurfaceContainerDark,
    surfaceContainerHigh = purplesurfaceContainerHighDark,
    surfaceContainerHighest = purplesurfaceContainerHighestDark,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun Ft_hangoutsTheme(
    targetColor: Color = Color.Unspecified,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when(targetColor) {
        bluePrimaryLight -> if (darkTheme) blueDarkScheme else blueLightScheme
        greenPrimaryLight -> if (darkTheme) greenDarkScheme else greenLightScheme
        purplePrimaryLight -> if (darkTheme) purpleDarkScheme else purpleLightScheme
        pinkPrimaryLight -> if (darkTheme) pinkDarkScheme else pinkLightScheme
        else -> if (darkTheme) greenDarkScheme else greenLightScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}