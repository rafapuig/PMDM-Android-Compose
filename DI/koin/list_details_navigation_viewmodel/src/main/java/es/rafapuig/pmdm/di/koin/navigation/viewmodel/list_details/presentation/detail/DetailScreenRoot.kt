package es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.presentation.detail

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreenRoot(
    id: Int,
    onBack: () -> Unit,
    viewModel: DetailViewModel = koinViewModel(parameters = { parametersOf(id) }), //viewModel { DetailViewModel(id) } //viewModel(factory = DetailViewModel.Factory(id))
) {
    DetailScreen(
        item = viewModel.item,
        onBack = onBack
    )
}