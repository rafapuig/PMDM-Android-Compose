package es.rafapuig.pmdm.compose.sensors.dashboard_animated_chart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.sensors.core.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.sensors.dashboard_animated_chart.ui.SensorsAnimatedChartDashboardViewModel
import es.rafapuig.pmdm.compose.sensors.dashboard_animated_chart.ui.navigation.routes.DashboardAnimatedChartScreenRoute

class DashBoardAnimatedChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                DashboardAnimatedChartScreenRoute(
                    viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                                SensorsAnimatedChartDashboardViewModel(applicationContext) as T
                        }
                    )
                )
            }
        }
    }
}