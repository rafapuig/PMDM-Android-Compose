package es.rafapuig.pmdm.di.hilt.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import es.rafapuig.pmdm.di.hilt.counter.presentation.screens.CounterScreenRoot
import es.rafapuig.pmdm.di.hilt.counter.ui.theme.PMDMComposeTheme

/** https://developer.android.com/training/dependency-injection/hilt-android#android-classes */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                CounterScreenRoot()
            }
        }
    }
}