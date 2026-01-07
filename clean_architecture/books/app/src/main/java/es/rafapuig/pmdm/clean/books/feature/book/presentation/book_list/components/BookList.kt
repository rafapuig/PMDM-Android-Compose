package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.BookListViewModel

@Composable
fun BookList(
    books: List<Book>,
    onBookClick: (Book) -> Unit,
    scrollState: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = scrollState,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            books,
            key = { book -> book.id }
        ) {book ->
            BookListItem(
                book = book,
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = { onBookClick(book) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookListPreview() {
    BookList(
        books = listOf(
            Book(
                id = "1",
                title = "Kotlin",
                authors = listOf("Rafael Puig", "Author 2"),
                averageRating = 4.555
            ),
            Book(
                id = "2",
                title = "Kotlin",
                authors = listOf("Rafael Puig", "Author 2"),
                averageRating = 4.555
            ),
            Book(
                id = "3",
                title = "Kotlin",
                authors = listOf("Rafael Puig", "Author 2"),
                averageRating = 4.555
            )
        ),
        onBookClick = {}
    )

}