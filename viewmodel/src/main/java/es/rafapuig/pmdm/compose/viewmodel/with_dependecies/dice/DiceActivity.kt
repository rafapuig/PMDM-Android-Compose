package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.data.DiceRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.DiceViewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.DiceViewModelFactory
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.screens.DiceScreen
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.screens.DiceScreenRoot

class DiceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {

                val viewModel = provideViewModel()

                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val onAction = viewModel::onAction

                DiceScreen(
                    uiState = uiState,
                    onAction = onAction
                )

                /** Tambien podemos llamar directamente a DiceScreenRoot */
                //DiceScreenRoot()
            }
        }
    }
}


@Composable
fun provideViewModel(): DiceViewModel {

    val factory1 = DiceViewModel.Factory
    val factory2 = DiceViewModelFactory(DiceRepositoryImpl())
    val factory3 = DiceViewModel.FactoryNotGood


    val viewModel1 = viewModel {
        DiceViewModel(
            DiceRepositoryImpl(),
            createSavedStateHandle()
        )
    }

    /** Mediante factorias */

    val viewModel2 = viewModel<DiceViewModel>(
        factory = DiceViewModelFactory(
            DiceRepositoryImpl()
        )
    )

    val viewModel3 = viewModel<DiceViewModel>(
        factory = DiceViewModel.FactoryNotGood
    )


    val extras = MutableCreationExtras().apply {
        set(DiceViewModel.DICE_REPOSITORY_KEY, DiceRepositoryImpl())
    }

    val viewModel4 = viewModel<DiceViewModel>(
        factory = DiceViewModel.Factory,
        extras = extras
    )


    return viewModel4
}
