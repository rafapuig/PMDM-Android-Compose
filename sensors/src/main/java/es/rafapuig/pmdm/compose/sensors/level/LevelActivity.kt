package es.rafapuig.pmdm.compose.sensors.level

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.compose.sensors.level.ui.screens.LevelScreen
import es.rafapuig.pmdm.compose.sensors.level.ui.screens.LevelScreenRoot
import es.rafapuig.pmdm.compose.sensors.ui.theme.PMDMComposeTheme

class LevelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            PMDMComposeTheme {
                LevelScreenRoot()
            }
        }
    }
}
