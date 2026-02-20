package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.feature.popular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.MoviesApplication
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class PopularMoviesViewModel(
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
            delay(2.seconds)
            try {
                // Pedimos la página actual
                val newMovies = repository.fetchPopularMovies(currentPage)
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

    private fun updatePopularMovies(movies: List<Movie>) {
        _movies.value = movies
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[APPLICATION_KEY] as MoviesApplication
                PopularMoviesViewModel(app.moviesRepository)
            }
        }
    }

}