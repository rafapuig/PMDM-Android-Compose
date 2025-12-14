package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.data.DiceRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.screens.DiceScreen
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.screens.DiceScreenRoot
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModelProvider
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers.DirectlyConstructionViewModelProvider
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers.FromFactoryNotGoodViewModelProvider
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers.FromFactoryWithDependencyViewModelProvider
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers.FromFactoryWithExtrasKeyViewModelProvider

class DiceActivity : ComponentActivity() {

    val repository = DiceRepositoryImpl()

    val directlyConstructionViewModelProvider: DiceViewModelProvider =
        DirectlyConstructionViewModelProvider(repository)

    val fromFactoryWithDependencyViewModelProvider: DiceViewModelProvider =
        FromFactoryWithDependencyViewModelProvider(repository)

    val fromFactoryNotGoodViewModelProvider : DiceViewModelProvider =
        FromFactoryNotGoodViewModelProvider(repository)

    val fromFactoryWithExtrasKeyViewModelProvider: DiceViewModelProvider =
        FromFactoryWithExtrasKeyViewModelProvider(repository)


    /**
     * Cambiar la asignación de esta linea por otra referencia
     * a otro implementador de DiceViewModelProvider
     * */
    val viewModelProvider: DiceViewModelProvider = fromFactoryWithExtrasKeyViewModelProvider

    //val viewModel: DiceViewModel by viewModels()
    val viewModel: DiceViewModel by with(viewModelProvider) { provideViewModelLazy() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {

                // Para usar el ViewModel de la Activity
                val viewModel = viewModel

                // Pasar usar un ViewModel de Compose
                //val viewModel = viewModelProvider.provideViewModel()

                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val onAction = viewModel::onAction

                DiceScreen(
                    uiState = uiState,
                    onAction = onAction
                )

                /** Tambien podemos llamar directamente a DiceScreenRoot */
                DiceScreenRoot(viewModel = viewModel)
            }
        }
    }
}