package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.clean.books.core.domain.onFailure
import es.rafapuig.pmdm.clean.books.core.domain.onSuccess
import es.rafapuig.pmdm.clean.books.core.presentation.toUiText
import es.rafapuig.pmdm.clean.books.feature.book.domain.BookRepository
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(
    private val repository: BookRepository
) : ViewModel() {

    var searchJob : Job? = null
    private var cachedBooks = emptyList<Book>()

    private val _state = MutableStateFlow(BookListState())
    val state = _state
        .onStart {
            if(cachedBooks.isEmpty()) {
                observeSearchQuery()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = _state.value
        )




    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnBookClick -> {

            }

            is BookListAction.OnSearchQueryChange -> {
                _state.update { it.copy(searchQuery = action.query) }
            }

            is BookListAction.OnTabSelected -> {
                _state.update { it.copy(selectedTabIndex = action.index) }
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() ->
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResults = cachedBooks
                            )
                        }

                    query.length >= 2 -> {
                        _state.update { it.copy(isLoading = true) }
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        repository
            .searchBooks(query)
            .onSuccess { books ->
                cachedBooks = books
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        searchResults = books
                    )
                }
            }
            .onFailure { error ->
                _state.update {
                    it.copy(
                        searchResults = emptyList(),
                        isLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
    }

}