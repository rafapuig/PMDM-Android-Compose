package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.dto.MovieDto

interface TMDBApiService {

    suspend fun getPopularMovies(page: Int = 1, region: String? = "ES"): List<MovieDto>

    suspend fun getTopRatedMovies(page: Int = 1, region: String? = "ES"): List<MovieDto>

}