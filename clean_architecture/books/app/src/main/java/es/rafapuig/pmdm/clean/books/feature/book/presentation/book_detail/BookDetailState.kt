package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_detail

import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
