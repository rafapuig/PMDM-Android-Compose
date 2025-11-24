package es.rafapuig.pmdm.compose.sensors.dashboard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.compose.sensors.core.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.sensors.ui.navigation.routes.DashboardScreenRoute

class DashBoardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                DashboardScreenRoute()
            }
        }
    }
}