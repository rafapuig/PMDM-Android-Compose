package es.rafapuig.pmdm.scoping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.scoping.presentation.NotePadViewModel
import es.rafapuig.pmdm.scoping.presentation.screens.NotePadScreen
import es.rafapuig.pmdm.scoping.ui.theme.PMDMComposeTheme

class ComposeViewModelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {

                /**
                 * En Compose obtenemos el ViewModelStoreOwner
                 * a través del LocalViewModelStoreOwner
                 * (En este codigo es equivalente a usar la referencia a la Activity)
                 */
                val owner =
                    LocalViewModelStoreOwner.current!!

                val owner2 = this as ViewModelStoreOwner

                val viewModel = viewModel<NotePadViewModel>(
                    viewModelStoreOwner = owner
                )

                val viewModel2 = viewModel<NotePadViewModel>(
                    viewModelStoreOwner = owner2
                )

                println("Son el mismo ViewModel? ${viewModel === viewModel2}")

                NotePadScreen(
                    text = viewModel.text,
                    onTextChange = viewModel::onTextChange
                )
            }
        }
    }
}