package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


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
            OportuniaDarkColorScheme
        }
        else -> {
            // Forzamos un dark theme o light theme en base a isSystemInDarkTheme()
            if (darkTheme) OportuniaDarkColorScheme else OportuniaDarkColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = OportuniaTypography,
        content = content
    )
}


//
//
//
//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)
//
//@Composable
//fun FrontendoportunialeandroTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}