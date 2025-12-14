package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.data.DiceRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModelFactory

@Composable
fun provideViewModel(): DiceViewModel {

    val factory1 = DiceViewModelFactory(DiceRepositoryImpl())
    val factory2 = DiceViewModel.Factory
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

    /** Mediante Factorias y extras */

    val activity = LocalViewModelStoreOwner.current as ComponentActivity
    val application = activity.application //as DiceApplication

    val defaultViewModelCreationExtras = CreationExtras.Empty

    val extras = MutableCreationExtras(activity.defaultViewModelCreationExtras).apply {
        set(DiceViewModel.DICE_REPOSITORY_KEY, DiceRepositoryImpl())
    }

    val viewModel4 = viewModel<DiceViewModel>(
        factory = DiceViewModel.Factory,
        extras = extras
    )


    return viewModel4
}