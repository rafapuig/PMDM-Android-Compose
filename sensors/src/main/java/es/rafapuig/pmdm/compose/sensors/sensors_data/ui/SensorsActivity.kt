package es.rafapuig.pmdm.compose.sensors.sensors_data.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.sensors.core.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.sensors.core.domain.repositories.SensorsRepositoryImpl
import es.rafapuig.pmdm.compose.sensors.ui.navigation.routes.SensorsScreenRoute

class SensorsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val repository = SensorsRepositoryImpl(applicationContext)

            val factory = SensorsViewModelWithRepository.provideFactory(repository)
            val factory2 = SensorsViewModelWithRepository.Factory

            val viewModel: SensorsViewModelWithRepository = viewModel(factory = factory)
            val viewModel2: SensorsViewModelWithApplication = viewModel()


            PMDMComposeTheme {
                SensorsScreenRoute(viewModel = viewModel2)
            }
        }
    }
}