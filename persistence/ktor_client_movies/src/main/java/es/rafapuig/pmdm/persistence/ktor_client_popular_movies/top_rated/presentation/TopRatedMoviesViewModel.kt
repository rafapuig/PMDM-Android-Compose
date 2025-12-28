package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.top_rated.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.MoviesApplication
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TopRatedMoviesViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _movies =
        MutableStateFlow<List<Movie>>(emptyList())

    val movies = _movies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var currentPage = 1

    init {
        onLoadMoreMovies()
    }

    // Función principal para cargar películas
    fun onLoadMoreMovies() {
        // Evitar múltiples llamadas si ya se está cargando
        if (isLoading.value) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Pedimos la página actual
                val newMovies = repository.fetchTopRatedMovies(currentPage)
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


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY] as MoviesApplication
                TopRatedMoviesViewModel(app.moviesRepository)
            }
        }
    }

}