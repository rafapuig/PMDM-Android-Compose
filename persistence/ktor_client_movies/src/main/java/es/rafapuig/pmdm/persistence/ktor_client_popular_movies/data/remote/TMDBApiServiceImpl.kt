package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiService.HttpRoutes.POPULAR_MOVIES_ENDPOINT
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiService.HttpRoutes.SEARCH_MOVIES_ENDPOINT
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiService.HttpRoutes.TOP_RATED_MOVIES_ENDPOINT
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.dto.MoviesApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class TMDBApiServiceImpl(
    private val client: HttpClient
) : TMDBApiService {

    override suspend fun getPopularMovies(
        page: Int,
        region: String?
    ): MoviesApiResponse = client
        .get(POPULAR_MOVIES_ENDPOINT) {
            //parameter("api_key", BuildConfig.API_KEY)
            //parameter("language", "es-ES")
            parameter("page", page)
            if (region != null) {
                parameter("region", region)
            }
        }.body()


    override suspend fun getTopRatedMovies(
        page: Int,
        region: String?
    ): MoviesApiResponse = client
        .get(TOP_RATED_MOVIES_ENDPOINT) {
            parameter("page", page)
            region?.let { parameter("region", it) }
        }.body()

    override suspend fun searchMovies(
        query: String,
        page: Int
    ): MoviesApiResponse {
        return client.get(SEARCH_MOVIES_ENDPOINT) {
            parameter("query", query)
            parameter("page", page)
        }.body()
    }

    companion object {
        fun create(): TMDBApiService {
            return TMDBApiServiceImpl(
                client = KtorClientProvider.client
            )
        }
    }

}