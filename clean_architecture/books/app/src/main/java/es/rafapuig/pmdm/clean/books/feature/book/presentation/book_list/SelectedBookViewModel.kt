package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list

import androidx.lifecycle.ViewModel
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedBookViewModel : ViewModel() {

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    fun onSelectedBook(book: Book?) {
        _selectedBook.value = book
    }

}