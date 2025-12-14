package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.DiceRepository
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModelProvider

class FromFactoryNotGoodViewModelProvider(
    private val repository: DiceRepository
) : DiceViewModelProvider {

    val factory = DiceViewModel.FactoryNotGood

    @Composable
    override fun provideViewModel(): DiceViewModel  = viewModel<DiceViewModel>(
        factory = factory
    )

    override fun ComponentActivity.provideViewModelLazy() : Lazy<DiceViewModel> = viewModels {
        factory
    }

}