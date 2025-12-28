package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.dto.MoviesApiResponse

interface TMDBApiService {

    suspend fun getPopularMovies(page: Int = 1, region: String? = "ES"): MoviesApiResponse

    suspend fun getTopRatedMovies(page: Int = 1, region: String? = "ES"): MoviesApiResponse

    suspend fun searchMovies(query: String, page: Int = 1): MoviesApiResponse


}