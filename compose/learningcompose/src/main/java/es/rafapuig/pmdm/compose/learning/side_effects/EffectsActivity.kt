package es.rafapuig.pmdm.compose.learning.side_effects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState.SearchListExample
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme

class EffectsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                RecompositionDemo()
            }
        }
    }
}
