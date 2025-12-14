package es.rafapuig.pmdm.compose.lifecycle.awareness

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
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

/**
 * https://developer.android.com/topic/libraries/architecture/lifecycle#kotlin
 */

class ObserverActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Registrar el observer en una Activity
        /** Hacemos que la activity sea observada por el Lifecycle observer */
        lifecycle.addObserver(MyObserver())


        /** Hacemos un LifecycleOwner sea observado por otro MyObserver */
        val owner = MyOwner()

        owner.lifecycle.addObserver(MyObserver())

        owner.resumeOwner()
        owner.pauseOwner()
        owner.stopOwner()
        owner.startOwner()


        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PMDMComposeTheme {
        Greeting2("Android")
    }
}