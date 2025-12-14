package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.DiceRepository
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModelProvider
import kotlin.reflect.KClass

class DirectlyConstructionViewModelProvider(
    private val repository: DiceRepository
) : DiceViewModelProvider {

    @Composable
    override fun provideViewModel(): DiceViewModel = viewModel {
        DiceViewModel(
            repository = repository,
            createSavedStateHandle()
        )
    }

    override fun ComponentActivity.provideViewModelLazy() = viewModels<DiceViewModel> {

        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                @Suppress("UNCHECKED_CAST")
                return DiceViewModel(
                    repository = repository,
                    extras.createSavedStateHandle()
                ) as T
            }
        }
    }
}



