package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_detail.components.BlurredImageBackground
import org.koin.androidx.compose.koinViewModel


@Composable
fun BookDetailScreenRoot(
    viewModel: BookDetailViewModel = koinViewModel(),
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state
        .collectAsStateWithLifecycle()

    BookDetailScreen(
        state = state,
        onAction = { action ->
            when (action) {
                BookDetailAction.OnBackClick -> onBack()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun BookDetailScreen(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit
) {
    BlurredImageBackground(
        imageUrl = state.book?.imageUrl,
        isFavorite = state.isFavorite,
        onFavoriteClick = { onAction(BookDetailAction.OnFavoriteClick) },
        onBackClick = { onAction(BookDetailAction.OnBackClick) },
        modifier = Modifier.fillMaxSize()
    ) {

    }
}