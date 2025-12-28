package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.repository

import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.model.Movie

interface MoviesRepository {

    suspend fun fetchPopularMovies(page: Int = 1) : List<Movie>
    suspend fun fetchTopRatedMovies(page: Int = 1) : List<Movie>
}