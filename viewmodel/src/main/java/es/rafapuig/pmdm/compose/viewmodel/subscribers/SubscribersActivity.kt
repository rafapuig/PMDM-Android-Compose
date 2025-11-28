package es.rafapuig.pmdm.compose.viewmodel.subscribers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.subscribers.ui.SubscribersViewModel
import es.rafapuig.pmdm.compose.viewmodel.subscribers.ui.SubscribersViewModelWithDependencies
import es.rafapuig.pmdm.compose.viewmodel.subscribers.ui.SubscribersViewModelWithDependency
import es.rafapuig.pmdm.compose.viewmodel.subscribers.ui.screens.SubscribersScreen
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

/**
 * Revisar siempre el Manifest para ver si esta crrectamente registada la Activity
 */
class SubscribersActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel : SubscribersViewModelWithDependency = viewModel(
                factory = SubscribersViewModelWithDependency.Factory
            )

            val viewModel2 = viewModel<SubscribersViewModel>()

            val viewModel3: SubscribersViewModelWithDependencies =
                viewModel(factory = SubscribersViewModelWithDependencies.Factory)

            val subscribers = viewModel3.subscribersFlow.collectAsState(0)

            PMDMComposeTheme {
                Surface() {
                    SubscribersScreen(subscribers.value)
                }
            }
        }
    }
}