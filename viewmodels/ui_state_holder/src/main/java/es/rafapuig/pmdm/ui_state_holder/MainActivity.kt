package es.rafapuig.pmdm.ui_state_holder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.ui_state_holder.compose_states.presentation.screens.NotePadScreenRoot
import es.rafapuig.pmdm.ui_state_holder.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                NotePadScreenRoot()
            }
        }
    }
}