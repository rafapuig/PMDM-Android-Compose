package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list

import es.rafapuig.pmdm.clean.books.core.presentation.UIText
import es.rafapuig.pmdm.clean.books.feature.book.data.books
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book

data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = books,
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UIText? = null

)
