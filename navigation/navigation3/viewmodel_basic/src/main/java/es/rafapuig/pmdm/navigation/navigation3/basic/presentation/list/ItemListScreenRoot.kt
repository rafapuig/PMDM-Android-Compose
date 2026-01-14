package es.rafapuig.pmdm.navigation.navigation3.basic.presentation.list

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ItemListScreenRoot(
    viewModel: ItemListViewModel = viewModel(),
    onNavigateToDetail: (Int) -> Unit = {}
) {
    ItemListScreen(
        items = viewModel.items,
        onItemClick = onNavigateToDetail
    )
}