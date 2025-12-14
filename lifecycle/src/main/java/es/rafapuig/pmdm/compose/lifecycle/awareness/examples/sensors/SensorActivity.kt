package es.rafapuig.pmdm.compose.lifecycle.awareness.examples.sensors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.compose.lifecycle.awareness.examples.sensors.ui.SensorScreenRoot
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class SensorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                SensorScreenRoot()
            }
        }
    }
}