package es.rafapuig.pmdm.compose.learning.layouts

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.pastel.PastelTheme

/**
 * Layout
 *
 * https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-api-guidelines.md#compose-ui-layouts
 *
 * Un elemento de UI de Compose que acepta uno o más parámetros de tipo función @Composable
 * se denomina layout
 *
 * Normalmente, las funciones composables layout:
 * - usan el nombre content para el parámetro de tipo función @Composable
 * - ubican este parámetro en la última posición de la lista de parámetros
 * para permitir el uso la sintaxis de Kotlin para lambdas como argumento del último parámetro.
 *
 */

@Composable
fun MyLayout(
    content: @Composable () -> Unit
) {
    PastelTheme {
        Surface (
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            content()
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MyLayoutDemo() {
    MyLayout {
        Text("Hola Layouts")
    }
}