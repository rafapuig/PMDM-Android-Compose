package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.domain.repository

import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.domain.model.Movie

interface MoviesRepository {

    suspend fun fetchPopularMovies(page: Int = 1): List<Movie>
    suspend fun fetchTopRatedMovies(page: Int = 1): List<Movie>
    suspend fun searchMovies(query: String, page: Int = 1): List<Movie>
}