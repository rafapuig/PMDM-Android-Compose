package es.rafapuig.pmdm.scoping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.scoping.presentation.screens.NotePadScreenRoot
import es.rafapuig.pmdm.scoping.ui.theme.PMDMComposeTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                NotePadScreenRoot()
            }
        }
    }
}