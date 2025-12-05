package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.data.DiceRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.screens.DiceScreen
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.DiceViewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.DiceViewModelFactory
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.screens.DiceScreenRoot

class DiceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {

                /*val viewModel = provideViewModel()

                val uiState by viewModel.uiState.collectAsState()
                val onAction = viewModel::onAction

                DiceScreen(
                    uiState = uiState,
                    onAction = onAction
                )*/

                /** Tambien podemos llamar directamente a DiceScreenRoot */
                DiceScreenRoot()
            }
        }
    }
}


@Composable
fun provideViewModel(): DiceViewModel {

    val factory1 = DiceViewModel.Factory
    val factory2 = DiceViewModelFactory(DiceRepositoryImpl())
    val factory3 = DiceViewModel.FactoryAlt

    val viewModel1 = viewModel<DiceViewModel>(
        factory = DiceViewModelFactory(
            DiceRepositoryImpl()
        )
    )

    val viewModel2 = viewModel {
        DiceViewModel(
            DiceRepositoryImpl(),
            createSavedStateHandle()
        )
    }

    val viewModel3 = viewModel<DiceViewModel>(
        factory = DiceViewModel.Factory
    )

    val viewModel4 = viewModel<DiceViewModel>(
        factory = DiceViewModel.FactoryAlt
    )

    return viewModel2
}
