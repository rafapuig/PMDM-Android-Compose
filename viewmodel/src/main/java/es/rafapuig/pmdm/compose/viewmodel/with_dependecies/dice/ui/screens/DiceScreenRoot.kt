package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.data.DiceRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModel
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModelProvider
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers.DirectlyConstructionViewModelProvider
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers.FromFactoryNotGoodViewModelProvider
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers.FromFactoryWithDependencyViewModelProvider
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.providers.FromFactoryWithExtrasKeyViewModelProvider

@Composable
fun DiceScreenRoot(
    viewModel: DiceViewModel =
        FromFactoryWithExtrasKeyViewModelProvider(DiceRepositoryImpl()).provideViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DiceScreen(uiState = uiState) { action ->
        viewModel.onAction(action)
    }
}


@Preview
@Composable
fun DiceScreenRootPreview1() {

    val viewModel = viewModel<DiceViewModel> {
        val repository = DiceRepositoryImpl()
        val savedStateHandle = createSavedStateHandle()
        DiceViewModel(repository, savedStateHandle)
    }

    PMDMComposeTheme {
        DiceScreenRoot(viewModel)
    }
}

@Preview
@Composable
fun DiceScreenRootPreview2() {

    val repository = DiceRepositoryImpl()

    val viewModel = viewModel<DiceViewModel>(
        factory = DiceViewModel.Factory,
        extras = MutableCreationExtras(
            LocalViewModelStoreOwner.current
                .let { viewModelStoreOwner ->
                    if (viewModelStoreOwner is HasDefaultViewModelProviderFactory)
                        viewModelStoreOwner.defaultViewModelCreationExtras
                    else
                        CreationExtras.Empty
                }
        ).apply {
            set(DiceViewModel.DICE_REPOSITORY_KEY, repository)
        }
    )

    PMDMComposeTheme {
        DiceScreenRoot(viewModel)
    }
}


@Preview
@Composable
fun DiceScreenRootDirectlyConstructionPreview() {

    val repository = DiceRepositoryImpl()

    val viewModelProvider: DiceViewModelProvider =
        DirectlyConstructionViewModelProvider(repository)

    PMDMComposeTheme {
        DiceScreenRoot(viewModelProvider.provideViewModel())
    }
}

@Preview
@Composable
fun DiceScreenRootFromFactoryWithDependencyPreview() {

    val repository = DiceRepositoryImpl()

    val viewModelProvider: DiceViewModelProvider =
        FromFactoryWithDependencyViewModelProvider(repository)

    PMDMComposeTheme {
        DiceScreenRoot(viewModelProvider.provideViewModel())
    }
}

@Preview
@Composable
fun DiceScreenRootFromFactoryNotGoodPreview() {

    val repository = DiceRepositoryImpl()

    val viewModelProvider: DiceViewModelProvider =
        FromFactoryNotGoodViewModelProvider(repository)

    PMDMComposeTheme {
        DiceScreenRoot(viewModelProvider.provideViewModel())
    }
}

@Preview
@Composable
fun DiceScreenRootFromFactoryWithExtrasKeyPreview() {

    val repository = DiceRepositoryImpl()

    val viewModelProvider: DiceViewModelProvider =
        FromFactoryWithExtrasKeyViewModelProvider(repository)

    PMDMComposeTheme {
        DiceScreenRoot(viewModelProvider.provideViewModel())
    }
}