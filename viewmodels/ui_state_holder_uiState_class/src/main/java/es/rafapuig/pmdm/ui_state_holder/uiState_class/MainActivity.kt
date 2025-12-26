package es.rafapuig.pmdm.ui_state_holder.uiState_class

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.ui_state_holder.uiState_class.core.presentation.screens.UiStateDemoScreen_NoEncapsulated
import es.rafapuig.pmdm.ui_state_holder.uiState_class.state_flows.presentation.UiStateDemoViewModel
import es.rafapuig.pmdm.ui_state_holder.uiState_class.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<UiStateDemoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                val uiState = viewModel.uiState
                    .collectAsStateWithLifecycle()

                UiStateDemoScreen_NoEncapsulated(
                    name = uiState.value.name,
                    isUpperCase = uiState.value.upperCase,
                    onNameChange = viewModel::onNameChange,
                    onUpperCaseChange = viewModel::onUpperCaseChange
                )
            }
        }
    }
}