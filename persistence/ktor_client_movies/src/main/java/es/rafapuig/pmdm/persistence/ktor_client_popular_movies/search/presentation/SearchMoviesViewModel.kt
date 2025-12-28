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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.skip
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    @OptIn(FlowPreview::class)
    private fun observeQueryChanges() {
        searchQuery
            .drop(1)
            .debounce(800)
            .filter { it.isNotBlank() }
            .onEach {
                currentPage = 1
                _movies.value = emptyList()
                onLoadMoreMovies()
            }
            .launchIn(viewModelScope)
    }

    // Función principal para cargar películas
    fun onLoadMoreMovies() {
        // Evitar múltiples llamadas si ya se está cargando
        if (isLoading.value) return

        viewModelScope.launch {
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