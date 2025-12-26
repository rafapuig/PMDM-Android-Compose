package es.rafapuig.pmdm.compose.viewmodel.subscribers_history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.subscribers_history.ui.ChartViewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.screens.ChartScreen
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

class SubscribersHistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel = viewModel<ChartViewModel>()

            val subscribersHistory by viewModel.subscribersHistory
                .collectAsState(initial = emptyList())

            PMDMComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChartScreen(
                        modifier = Modifier.padding(innerPadding),
                        subscribersHistory = subscribersHistory
                    )
                }
            }
        }
    }
}
