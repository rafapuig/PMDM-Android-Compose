package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.repositories

import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.TMDBApiService
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.mappers.toDomain
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.repository.MoviesRepository

/**
 * Implementación de la interface MoviesRepository definida en la capa de dominio
 *
 * Para realizar su labor depende de una instancia que cuya clase
 * implemente la interface TMDBApiService
 */
class MoviesRepositoryRemoteImpl(
    private val api: TMDBApiService
) : MoviesRepository {

    override suspend fun fetchPopularMovies(page: Int): List<Movie> {
        return api.fetchPopularMovies(page).results
            .map { movieDto -> movieDto.toDomain() }
    }

    override suspend fun fetchTopRatedMovies(page: Int) =
        api.fetchTopRatedMovies(page).results.map {
            it.toDomain()
        }

    override suspend fun searchMovies(
        query: String,
        page: Int
    ) = api.searchMovies(query, page).results.map {
        it.toDomain()
    }

}