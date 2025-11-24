package es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.sensors.core.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.sensors.ui.SensorsChartDashboardViewModel
import es.rafapuig.pmdm.compose.sensors.ui.navigation.routes.DashboardChartScreenRoute
import kotlin.reflect.KClass

class DashBoardChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            PMDMComposeTheme {
                DashboardChartScreenRoute(
                    viewModel(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(
                                modelClass: KClass<T>,
                                extras: CreationExtras
                            ): T = run {
                                val application =
                                    extras[ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY] as Application
                                val context = application.applicationContext
                                SensorsChartDashboardViewModel(context) as T
                            }
                        }
                    )
                )
            }
        }
    }
}