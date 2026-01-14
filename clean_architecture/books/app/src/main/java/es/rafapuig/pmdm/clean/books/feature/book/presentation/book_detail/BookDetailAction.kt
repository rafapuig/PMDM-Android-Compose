package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_detail

import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book

sealed interface BookDetailAction {
    data object OnBackClick : BookDetailAction
    data object OnFavoriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}