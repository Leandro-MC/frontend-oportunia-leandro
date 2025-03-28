package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val OportuniaDarkColorScheme = darkColorScheme(
    primary = RedPrimary,
    onPrimary = WhiteText,
    secondary = BlueLink,
    onSecondary = WhiteText,
    background = DarkBackground,
    onBackground = WhiteText,
    surface = GraySurface,
    onSurface = WhiteText,
    error = ErrorColor,
    onError = WhiteText
)


private val OportuniaLightColorScheme = lightColorScheme(
    primary = RedPrimary,
    onPrimary = WhiteText,
    secondary = BlueLink,
    onSecondary = WhiteText,
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFEAEAEA),
    onSurface = Color(0xFF000000),
    error = ErrorColor,
    onError = WhiteText
)

/**
 * Tema principal de la app.
 * Ponemos `dynamicColor = false` por defecto para que no use colores dinámicos.
 */
@Composable
fun FrontendoportuniaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Cambiamos a false para que NO use los colores dinámicos.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            // implementar lógica dinámica
            OportuniaDarkColorScheme
        }
        else -> {
            OportuniaDarkColorScheme
//            if (darkTheme) OportuniaDarkColorScheme else OportuniaLightColorScheme
        }
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = OportuniaTypography,
        content = content
    )
}

