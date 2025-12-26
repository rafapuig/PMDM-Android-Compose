package es.rafapuig.pmdm.clean.subscribers.viewmodel_logic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import es.rafapuig.pmdm.clean.subscribers.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.clean.subscribers.viewmodel_logic.presentation.screens.SubscribersScreenRoot

class SubscribersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Surface {
                    SubscribersScreenRoot()
                }
            }
        }
    }
}