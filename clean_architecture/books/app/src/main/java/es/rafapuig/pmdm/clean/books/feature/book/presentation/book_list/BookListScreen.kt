package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.components.BookSearchBar
import es.rafapuig.pmdm.clean.books.ui.theme.DarkBlue

@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel, //= koinViewModel()
    onNavigateToBookDetails: (Book) -> Unit = {}
) {
    val state by viewModel.state
        .collectAsStateWithLifecycle()

    BookListScreen(
        state = state,
        onAction = {action ->
            when (action) {
                is BookListAction.OnBookClick -> onNavigateToBookDetails(action.book)
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
private fun BookListScreen(
    state: BookListState,
    onAction: (BookListAction) -> Unit
) {
    val keyboardController =
        LocalSoftwareKeyboardController.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(BookListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )

    }

}

@Preview(showSystemUi = true)
@Composable
fun BookListScreenPreview() {
    BookListScreen(
        state = BookListState(),
        onAction = {}
    )
}