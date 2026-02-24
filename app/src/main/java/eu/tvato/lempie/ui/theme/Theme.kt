package eu.tvato.lempie.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    inversePrimary = inversePrimaryDark,

    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,

    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,

    background = backgroundDark,
    onBackground = onBackgroundDark,

    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    surfaceTint = surfaceTintDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,

    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,

    outline = outlineDark,
    outlineVariant = outlineVariantDark,

    scrim = scrimDark,

    surfaceBright = surfaceBrightDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceDim = surfaceDimDark,

    primaryFixed = primaryFixedDark,
    primaryFixedDim = primaryFixedDimDark,
    onPrimaryFixed = onPrimaryFixedDark,
    onPrimaryFixedVariant = onPrimaryFixedVariantDark,

    secondaryFixed = secondaryFixedDark,
    secondaryFixedDim = secondaryFixedDimDark,
    onSecondaryFixed = onSecondaryFixedDark,
    onSecondaryFixedVariant = onSecondaryFixedVariantDark,

    tertiaryFixed = tertiaryFixedDark,
    tertiaryFixedDim = tertiaryFixedDimDark,
    onTertiaryFixed = onTertiaryFixedDark,
    onTertiaryFixedVariant = onTertiaryFixedVariantDark,
)

val DarkGenColorScheme = darkColorScheme(
    primary = primaryDarkGen,
    onPrimary = onPrimaryDarkGen,
    primaryContainer = primaryContainerDarkGen,
    onPrimaryContainer = onPrimaryContainerDarkGen,
    inversePrimary = inversePrimaryDarkGen,

    secondary = secondaryDarkGen,
    onSecondary = onSecondaryDarkGen,
    secondaryContainer = secondaryContainerDarkGen,
    onSecondaryContainer = onSecondaryContainerDarkGen,

    tertiary = tertiaryDarkGen,
    onTertiary = onTertiaryDarkGen,
    tertiaryContainer = tertiaryContainerDarkGen,
    onTertiaryContainer = onTertiaryContainerDarkGen,

    background = backgroundDarkGen,
    onBackground = onBackgroundDarkGen,

    surface = surfaceDarkGen,
    onSurface = onSurfaceDarkGen,
    surfaceVariant = surfaceVariantDarkGen,
    onSurfaceVariant = onSurfaceVariantDarkGen,
    inverseSurface = inverseSurfaceDarkGen,
    inverseOnSurface = inverseOnSurfaceDarkGen,

    error = errorDarkGen,
    onError = onErrorDarkGen,
    errorContainer = errorContainerDarkGen,
    onErrorContainer = onErrorContainerDarkGen,

    outline = outlineDarkGen,
    outlineVariant = outlineVariantDarkGen,

    scrim = scrimDarkGen,

    surfaceBright = surfaceBrightDarkGen,
    surfaceContainer = surfaceContainerDarkGen,
    surfaceContainerHigh = surfaceContainerHighDarkGen,
    surfaceContainerHighest = surfaceContainerHighestDarkGen,
    surfaceContainerLow = surfaceContainerLowDarkGen,
    surfaceContainerLowest = surfaceContainerLowestDarkGen,
    surfaceDim = surfaceDimDarkGen,
)

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

enum class Theme {
    Light,
    Dark,
    DarkGen
}

/*
        To add colors with custom names...

val ColorScheme.newColor: Color
    @Composable
    get() = Color.Red

*/

@Composable
fun LemPieTheme(
    theme: Theme = if(isSystemInDarkTheme()) Theme.Dark else Theme.Light,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if(theme == Theme.Dark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        theme == Theme.Dark -> DarkColorScheme
        theme == Theme.Light -> LightColorScheme
        theme == Theme.DarkGen -> DarkGenColorScheme
        else -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}