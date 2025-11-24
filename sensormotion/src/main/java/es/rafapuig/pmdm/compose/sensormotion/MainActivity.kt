package es.rafapuig.pmdm.compose.sensormotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.sensormotion.ui.SensorViewModel
import es.rafapuig.pmdm.compose.sensormotion.ui.screens.BallScreen
import es.rafapuig.pmdm.compose.sensormotion.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel : SensorViewModel = viewModel()

            val lifecycleOwner = LocalLifecycleOwner.current //this.lifecycle

            val normalizedFlow = remember {
                viewModel
                    .normalized
                    .flowWithLifecycle(
                        lifecycleOwner.lifecycle,
                        Lifecycle.State.STARTED)
            }

            val normalized by normalizedFlow
                .collectAsState(Pair(0f, 0f))

            PMDMComposeTheme {
                BallScreen(normalized)
            }
        }
    }
}