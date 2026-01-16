package es.rafapuig.pmdm.navigation.navigation3.basic.presentation.detail

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreenRoot(
    id: Int,
    onBack: () -> Unit,
    viewModel: DetailViewModel = viewModel { DetailViewModel(id) } //viewModel(factory = DetailViewModel.Factory(id))
) {
    DetailScreen(
        item = viewModel.item,
        onBack = onBack
    )
}