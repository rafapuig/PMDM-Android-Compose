package es.rafapuig.pmdm.emptyactivity

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
import es.rafapuig.pmdm.emptyactivity.ui.theme.PMDMComposeTheme

/**
 * https://developer.android.com/guide/components/activities/intro-activities
 */
// CTRL + Clic sobre MainActivity para ir a su declaración en el manifiesto
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Este método pertenece a la biblioteca base androidx.activity:activity
         * (introducido en las versiones recientes para reflejar la pantalla completa
         * detrás de las barras del sistema)
         */
        /**
         * Sin Edge-to-Edge: Tu app está encajonada. Las barras del sistema son paredes sólidas.
         * Con Edge-to-Edge: Tu app ocupa toda la pantalla. Las barras del sistema son capas transparentes que flotan sobre tu app
         */
        enableEdgeToEdge()

        /**
         * El método setContent { ... } no pertenece a la biblioteca de Compose (androidx.compose.ui).
         * Es un método de extensión creado específicamente para la clase ComponentActivity,
         * y está alojado únicamente dentro de la biblioteca androidx.activity:activity-compose
         */
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PMDMComposeTheme {
        Greeting("Android")
    }
}