package es.rafapuig.pmdm.compose.learning.components.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.modifiers.ModifiersDemoScreen


/**
 * Un Scaffold es una función composable que srive de andamiaje, (esqueleto). estructura
 * de la intefaz de usuario.
 * A este esqueleto de UI se le acoplan o ensamblan otros componentes como:
 * - App Bar
 * - floating action button
 * - etc
 * Proporcionando a la aplicación un Look-and-feel coherente
 *
 * Los componentes a ensamblar en el Scaffold se pason como argumentos para
 * algunos de los parametros declarados en la función Scaffold:
 * - topBar
 * - bottomBar
 * - floatingActionButton
 * - etc..
 *
 * El Scaffold acepta un composable como contenido mediante el ultimo parámetro: content
 * A la lambda que sirve de argumento de content se le pasa un PaddingValues
 *
 * El valor del PaddingValues lo debemos aplicar al composable raiz de nuestra jeraquia de composables
 * que forman el contenido del Scaffold para restringir el tamaño del composable raiz. De esta forma,
 * no se superpone el contenido con los elementos del sistema como las notificaciones, la hora, etc.
 */

@Preview(showSystemUi = true)
@Composable
fun ScaffoldPaddingDemo() {
    Scaffold { innerPadding -> // padding para dejar espacio para elems del sistema
        Box( // Composable raíz de la jerarquía de composables del contenido del Scaffold
            modifier = Modifier
                .padding(innerPadding) // aplicamos el padding proporcionado por el Scaffold
                .fillMaxSize()
                .background(Color.Cyan) // Veremos que no invade las areas superior e inferior del sistema
        )
    }
}

