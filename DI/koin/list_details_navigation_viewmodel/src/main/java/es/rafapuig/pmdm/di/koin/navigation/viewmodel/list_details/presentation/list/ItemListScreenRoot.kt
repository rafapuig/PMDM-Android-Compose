package es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ItemListScreenRoot(
    viewModel: ItemListViewModel = koinViewModel(),
    onNavigateToDetail: (Int) -> Unit = {}
) {

    val items by viewModel.itemsFlow
        .collectAsStateWithLifecycle()

    ItemListScreen(
        items = items,
        onItemClick = onNavigateToDetail
    )
}