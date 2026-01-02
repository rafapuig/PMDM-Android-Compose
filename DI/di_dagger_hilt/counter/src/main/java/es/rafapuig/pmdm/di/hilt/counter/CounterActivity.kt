package es.rafapuig.pmdm.di.hilt.counter

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
import dagger.hilt.android.AndroidEntryPoint
import es.rafapuig.pmdm.di.hilt.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.di.hilt.counter.presentation.screens.CounterScreenRoot
import es.rafapuig.pmdm.di.hilt.counter.ui.theme.PMDMComposeTheme


/** https://developer.android.com/training/dependency-injection/hilt-jetpack#viewmodels */

@AndroidEntryPoint
class CounterActivity : ComponentActivity() {

    /**
     * La función viewModels() obtiene el ViewModel generado por Hilt
     */
    val viewModel: CounterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                CounterScreenRoot(viewModel)
            }
        }
    }
}
