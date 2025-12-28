package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.repository.MoviesRepository

class MoviesRepositoryRemoteImpl(
    private val api: TMDBApiService
) : MoviesRepository {

    override suspend fun fetchPopularMovies(page: Int): List<Movie> {
        return api.getPopularMovies(page).map {
            it.toDomain()
        }
    }

    override suspend fun fetchTopRatedMovies(page: Int): List<Movie> {
        return api.getTopRatedMovies(page).map {
            it.toDomain()
        }
    }
}