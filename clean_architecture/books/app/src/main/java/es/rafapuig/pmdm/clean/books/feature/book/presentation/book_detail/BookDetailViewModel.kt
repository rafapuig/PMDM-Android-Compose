package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookDetailViewModel : ViewModel() {

    private val _state =
        MutableStateFlow(BookDetailState())
    val state = _state.asStateFlow()


    fun onAction(action: BookDetailAction) {

    }

}