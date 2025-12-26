package es.rafapuig.pmdm.ui_state_holder.uiState_class.compose_states

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.ui_state_holder.uiState_class.compose_states.presentation.screens.UiStateDemoScreenRoot
import es.rafapuig.pmdm.ui_state_holder.uiState_class.ui.theme.PMDMComposeTheme

class ComposeStatesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                UiStateDemoScreenRoot()
            }
        }
    }
}