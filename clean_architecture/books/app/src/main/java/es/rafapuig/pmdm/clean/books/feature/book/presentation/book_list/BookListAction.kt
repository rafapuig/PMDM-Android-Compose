package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list

import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book

sealed interface BookListAction {
    data class OnSearchQueryChange(val query: String) : BookListAction
    data class OnBookClick(val book: Book) : BookListAction
    data class OnTabSelected(val index: Int) : BookListAction
}