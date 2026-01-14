package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list

import es.rafapuig.pmdm.clean.books.core.presentation.UIText
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book

data class BookListState(
    val searchQuery: String = "kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UIText? = null

)
