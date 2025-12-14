package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.data.DiceRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.DiceRepository
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModelProvider

class FromFactoryWithExtrasKeyViewModelProvider(
    private val repository: DiceRepository
) : DiceViewModelProvider {

    val factory = DiceViewModel.Factory

    @Composable
    override fun provideViewModel(): DiceViewModel {

        val viewModelStoreOwner = LocalViewModelStoreOwner.current

        val initialExtras =
            if (viewModelStoreOwner is HasDefaultViewModelProviderFactory) {
                viewModelStoreOwner.defaultViewModelCreationExtras
            } else {
                CreationExtras.Empty
            }

        val extras = MutableCreationExtras(initialExtras).apply {
            set(DiceViewModel.DICE_REPOSITORY_KEY, repository)
        }

        return viewModel<DiceViewModel>(
            factory = factory,
            extras = extras
        )
    }

    override fun ComponentActivity.provideViewModelLazy(): Lazy<DiceViewModel> = viewModels(
        factoryProducer = { factory },
        extrasProducer = {
            MutableCreationExtras(defaultViewModelCreationExtras).apply {
                set(DiceViewModel.DICE_REPOSITORY_KEY, repository)
            }
        }
    )
}