package es.rafapuig.pmdm.di.users.koin.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.di.users.koin.compose.presentation.greeting.GreetingUserScreenRoot
import es.rafapuig.pmdm.di.users.koin.compose.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                GreetingUserScreenRoot()
            }
        }
    }
}
