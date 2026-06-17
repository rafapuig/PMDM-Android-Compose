package es.rafapuig.pmdm.compose.learning.layout.layouts

import android.content.res.Configuration
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.pastel.PastelTheme

/**
 * El proceso de layout (ubicación) de un elemento de UI
 * https://developer.android.com/develop/ui/compose/layouts/basics#model
 *
 * Compose transforma estado de UI en elementos de UI en tres etapas:
 * 1. Composición
 * 2. Layout (ubicación) <------
 * 3. Renderización
 *
 * https://developer.android.com/static/develop/ui/compose/images/composition-layout-drawing.svg
 *
 * Fundamentals of Compose Layouts and Modifiers
 * https://www.youtube.com/watch?v=xc8nAcVvpxY
 */


/**
 * Layouts
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
        Surface(
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

/**
 *
 *
 * Organización de composables mediante layouts
 * https://developer.android.com/develop/ui/compose/layouts/basics
 */
