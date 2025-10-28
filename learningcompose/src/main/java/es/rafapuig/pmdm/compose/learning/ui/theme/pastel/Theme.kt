package es.rafapuig.pmdm.compose.learning.ui.theme.pastel

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import es.rafapuig.pmdm.compose.learning.ui.theme.Pink40
import es.rafapuig.pmdm.compose.learning.ui.theme.Pink80
import es.rafapuig.pmdm.compose.learning.ui.theme.Purple40
import es.rafapuig.pmdm.compose.learning.ui.theme.Purple80
import es.rafapuig.pmdm.compose.learning.ui.theme.PurpleGrey40
import es.rafapuig.pmdm.compose.learning.ui.theme.PurpleGrey80
import es.rafapuig.pmdm.compose.learning.ui.theme.Typography


@Composable
fun PastelComposeTheme(
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

    /**
     * Un tema de Material 3 esta compuesto por 2 subsistemas:
     * - El sistema de colores (schemeColor)
     * - El sistema de tipografía (typography)
     * - El sistema de formas (shapes)
     *
     * Cuando personalizamos estos valores los cambios se reflejan
     * automaticamente en los componentes de Material 3 que usemos
     * en nuestra app.
     */

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = MaterialTheme.shapes,
        typography = Typography,
        content = content // contenido de una App que usa este tema
    )
}