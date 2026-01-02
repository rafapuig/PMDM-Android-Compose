package es.rafapuig.pmdm.di.counter.hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.rafapuig.pmdm.di.counter.hilt.presentation.CounterScreenRoot
import es.rafapuig.pmdm.di.counter.hilt.ui.theme.PMDMComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                CounterScreenRoot(hiltViewModel())
            }
        }
    }
}
