package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.MoviesApplication
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.skip
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SearchMoviesViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _movies =
        MutableStateFlow<List<Movie>>(emptyList())

    val movies = _movies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    private val _query = MutableStateFlow("")
    val searchQuery = _query.asStateFlow()


    private var currentPage = 1

    init {
        //onLoadMoreMovies()
        observeQueryChanges()
    }


    /**
     * “Si llamo a una suspend fun, uso collectLatest”
     * Si mi repo devuelve un Flow, uso flatMapLatest”
     */
    @OptIn(FlowPreview::class)
    private fun observeQueryChanges() {
        viewModelScope.launch {
            searchQuery
                //.drop(1)
                .map { it.trim() } // ✂️ Normaliza el input en el ViewModel, no en la UI
                .onEach { query ->
                    if (query.isBlank()) {
                        _movies.value = emptyList()
                    }
                }
                .debounce(1.seconds)
                .distinctUntilChanged()
                .filter { it.isNotBlank() && it.length > 2 }
                .collectLatest { query ->
                    currentPage = 1
                    _movies.value = emptyList()
                    loadMoreMovies()
                }
        }
    }

    suspend fun loadMoreMovies() {
        _isLoading.value = true
        try {
            // Pedimos la página actual
            val newMovies = repository.searchMovies(searchQuery.value, currentPage)
            // Añadimos las nuevas películas a la lista existente
            _movies.update { it + newMovies }
            // Incrementamos el número de página para la próxima vez
            currentPage++
        } catch (e: Exception) {
            // Manejar el error (p. ej., mostrar un Toast o un mensaje en la UI)
            println("Error al cargar películas: ${e.message}")
        } finally {
            _isLoading.value = false
        }
    }


    // Función principal para cargar películas
    fun onLoadMoreMovies() {
        // Evitar múltiples llamadas si ya se está cargando
        if (isLoading.value) return

        viewModelScope.launch {
            loadMoreMovies()
        }
    }

    fun onSearchQueryChanged(query: String) {
        _query.value = query
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY] as MoviesApplication
                SearchMoviesViewModel(app.moviesRepository)
            }
        }
    }

}