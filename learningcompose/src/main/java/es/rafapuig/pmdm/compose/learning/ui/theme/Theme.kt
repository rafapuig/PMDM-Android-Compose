package es.rafapuig.pmdm.compose.learning.ui.theme

import android.app.Activity
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

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF64B5F6),
    onPrimary = Color(0xFF003C8F),
    primaryContainer = Color(0xFF004B7A), //en TopAppBar/Card/Chip
    onPrimaryContainer = Color(0xFFD6EEFF),

    secondary = Color(0xFF81C784),
    onSecondary = Color(0xFF002E18),
    secondaryContainer = Color(0xFF004321),
    onSecondaryContainer = Color(0xFFCFF9D7),

    tertiary = Color(0xFFFFAB91),
    onTertiary = Color(0xFF3E2723),
    tertiaryContainer = Color(0xFF5C2C21),
    onTertiaryContainer = Color(0xFFFFE0CC),

    background = Color(0xFF0F1115),
    onBackground = Color(0xFFECECEC),

    surface = Color(0xFF121316),
    onSurface = Color(0xFFECECEC),

    surfaceVariant = Color(0xFF2D2B30), //para Field y Card secundarias
    onSurfaceVariant = Color(0xFFCBC6CE),

    outline = Color(0xFF9A959E),

    inverseSurface = Color(0xFFECECEC), //para bottom sheets o modos invertidos
    inverseOnSurface = Color(0xFF19191A),
    inversePrimary = Color(0xFF0066CC),

    error = Color(0xFFCF6679),
    onError = Color(0xFF410014),
    errorContainer = Color(0xFF5D121E),
    onErrorContainer = Color(0xFFFFDAD6)
)




private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color(0xFF0D47A1),
    primaryContainer = Color(0xFFD1E9FF),
    onPrimaryContainer = Color(0xFF002E5C),

    secondary = Color(0xFFA5D6A7),
    onSecondary = Color(0xFF1B5E20),
    secondaryContainer = Color(0xFFDFF7E6),
    onSecondaryContainer = Color(0xFF002C14),

    tertiary = Color(0xFFFFCCBC),
    onTertiary = Color(0xFF4E342E),
    tertiaryContainer = Color(0xFFFFEDE6),
    onTertiaryContainer = Color(0xFF3B231D),

    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),

    surface = Color(0xFFF9F9F9),
    onSurface = Color(0xFF1C1B1F),

    surfaceVariant = Color(0xFFE6E1E5),
    onSurfaceVariant = Color(0xFF49454F),

    outline = Color(0xFF79747E),

    inverseSurface = Color(0xFF2C2C2E),
    inverseOnSurface = Color(0xFFF2F0F3),
    inversePrimary = Color(0xFF6EA9FF),

    error = Color(0xFFEF9A9A),
    onError = Color(0xFF680005),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002)
)

@Composable
fun PMDMComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Puesto a false
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors //DarkColorScheme
        else -> LightColors //LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}