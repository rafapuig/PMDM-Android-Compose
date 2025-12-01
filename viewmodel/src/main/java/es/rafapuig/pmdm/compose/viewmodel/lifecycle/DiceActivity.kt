package es.rafapuig.pmdm.compose.viewmodel.lifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.data.DiceRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.DiceScreen
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.DiceViewModel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.DiceViewModelFactory
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

class DiceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {

                val viewModel = viewModel<DiceViewModel>(
                    factory = DiceViewModelFactory(
                        DiceRepositoryImpl()
                    )
                )

                val uiState by viewModel.uiState.collectAsState()
                val onAction = viewModel::onAction

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { Text("Dado") }
                ) { innerPadding ->
                    DiceScreen(
                        uiState = uiState,
                        onAction = onAction,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}