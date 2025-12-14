package es.rafapuig.pmdm.compose.viewmodel.lifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.navigation.AppNavHost
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

class LifecycleMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                AppNavHost()
            }
        }
    }
}
