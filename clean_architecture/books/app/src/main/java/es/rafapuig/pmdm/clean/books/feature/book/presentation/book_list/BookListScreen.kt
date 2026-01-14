package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.clean.books.R
import es.rafapuig.pmdm.clean.books.feature.book.data.dummyBooks
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.components.BookList
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.components.BookSearchBar
import es.rafapuig.pmdm.clean.books.ui.theme.DarkBlue
import es.rafapuig.pmdm.clean.books.ui.theme.DesertWhite
import es.rafapuig.pmdm.clean.books.ui.theme.SandYellow
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    onNavigateToBookDetails: (Book) -> Unit = {}
) {
    val state by viewModel.state
        .collectAsStateWithLifecycle()

    BookListScreen(
        state = state,
        onAction = { action ->
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

    val pagerState = rememberPagerState { 2 }

    /**
     * Hacer que se cambie de pagina cuando el usuario pulsa sobre una tab
     */
    LaunchedEffect(state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    /**
     * Hacer que se cambie la tab seleccionada cuando mediante un swipe
     * el usuario cambia de página
     */
    LaunchedEffect(pagerState.currentPage) {
        onAction(BookListAction.OnTabSelected(pagerState.currentPage))
    }

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

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = if (!isSystemInDarkTheme()) DesertWhite else MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {


                SecondaryTabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    indicator = {
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier = Modifier
                                .tabIndicatorOffset(state.selectedTabIndex)
                        )
                    }
                ) {
                    val tabs = listOf(
                        stringResource(R.string.search_results),
                        stringResource(R.string.favorites)
                    )

                    tabs.forEachIndexed { index, text ->
                        Tab(
                            selected = state.selectedTabIndex == index,
                            onClick = {
                                onAction(BookListAction.OnTabSelected(index))
                            },
                            //modifier = Modifier.weight(1f),
                            selectedContentColor = SandYellow,
                            unselectedContentColor = MaterialTheme.colorScheme.onSurface
                        ) {
                            Text(
                                text = text,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) { pageIndex ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (pageIndex) {
                            0 -> SearchResultsPageContent(state, onAction)
                            1 -> FavoritesPageContent(state, onAction)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultsPageContent(
    state: BookListState,
    onAction: (BookListAction) -> Unit
) {
    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        when {
            state.errorMessage != null -> {
                MessageText(state.errorMessage.asString())
            }

            state.searchResults.isEmpty() -> {
                MessageText(stringResource(R.string.no_search_results))
            }

            else -> {
                BookList(
                    books = state.searchResults,
                    onBookClick = {
                        onAction(BookListAction.OnBookClick(it))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun FavoritesPageContent(
    state: BookListState,
    onAction: (BookListAction) -> Unit
) {
    if (state.favoriteBooks.isEmpty()) {
        MessageText(stringResource(R.string.no_favorites))
    } else {
        BookList(
            books = state.favoriteBooks,
            onBookClick = {
                onAction(BookListAction.OnBookClick(it))
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun MessageText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.error
    )
}


@Preview(showSystemUi = true)
@Composable
fun BookListScreenPreview() {

    BookListScreen(
        state = BookListState(
            //errorMessage = UIText.DynamicString("Error message"),
            searchResults = dummyBooks
        ),
        onAction = {}
    )
}