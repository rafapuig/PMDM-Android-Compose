package es.rafapuig.pmdm.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.intro.ui.theme.PMDMComposeTheme

/**
 * La clase de la actividad debe estar declarada
 * en el manifiesto de la aplicación
 * AndroidManifest.xml
 *
 * Si pulsas en el widget </> a la izquierda de la
 * clase puedes ir a su declaración en el manifiesto
 *
 * Para que una actividad se pueda lanzar como actividad
 * inicial desde el launcher
 * se debe exportar en el manifiesto
 * y se debe declarar en el intent-filter
 *
 * Para crear una UI en Compose y mostrarla como contenido
 * de la actividad esta debe extender la clase ComponentActivity()
 */
class MainActivity : ComponentActivity() {

    /**
     * Se llama al callback onCreate()
     * una vez la actividad ha sido adjuntada a la aplicación
     * y se ha creado su contexto
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /**
         * Se llama a setContent() para establecer el contenido de la actividad.
         * Llamamos a la función del tema personalizado
         * PMDMComposeTheme() en este caso
         * y llamamos a la función MainScreen()
         * que se encargará de componer la pantalla de la UI
         */
        setContent {
            PMDMComposeTheme {
                MainScreen()
            }
        }
    }
}