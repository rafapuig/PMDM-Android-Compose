package es.rafapuig.pmdm.clean.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.clean.authentication.auth.navigation.NavigationRoot
import es.rafapuig.pmdm.clean.authentication.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                NavigationRoot()
            }
        }
    }
}