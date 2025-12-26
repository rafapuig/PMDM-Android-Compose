package es.rafapuig.pmdm.ui_state_holder.uiState_class.state_flows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.ui_state_holder.uiState_class.state_flows.presentation.screens.UiStateDemoScreenRoot
import es.rafapuig.pmdm.ui_state_holder.uiState_class.ui.theme.PMDMComposeTheme

class StateFlowsActivity : ComponentActivity() {
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