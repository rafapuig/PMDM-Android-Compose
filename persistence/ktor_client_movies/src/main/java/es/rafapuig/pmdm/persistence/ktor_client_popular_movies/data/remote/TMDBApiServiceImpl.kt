package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.BuildConfig
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiServiceImpl.HttpRoutes.API_HOST
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiServiceImpl.HttpRoutes.API_VERSION_PATH
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiServiceImpl.HttpRoutes.POPULAR_MOVIES_ENDPOINT
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiServiceImpl.HttpRoutes.SEARCH_MOVIES_ENDPOINT
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiServiceImpl.HttpRoutes.TOP_RATED_MOVIES_ENDPOINT
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.dto.MovieDto
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.dto.MoviesApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class TMDBApiServiceImpl(
    private val client: HttpClient
) : TMDBApiService {

    object HttpRoutes {
        const val API_HOST = "api.themoviedb.org"
        const val API_VERSION_PATH = "3"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val POPULAR_MOVIES_ENDPOINT = "movie/popular"
        const val TOP_RATED_MOVIES_ENDPOINT = "movie/top_rated"
        const val SEARCH_MOVIES_ENDPOINT = "search/movie"
    }

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
                client = HttpClient(Android) {
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        })
                    }

                    defaultRequest {
                        url {
                            protocol = URLProtocol.HTTPS
                            host = API_HOST
                            encodedPath = "/$API_VERSION_PATH/"
                            parameters.apply {
                                //append("api_key", BuildConfig.API_KEY)
                                append("language", "es-ES")
                            }
                            headers.append(
                                "Authorization",
                                "Bearer ${BuildConfig.API_ACCESS_TOKEN}"
                            )
                        }
                    }


                    install(Logging) {
                        level = LogLevel.ALL
                        logger = object : Logger {
                            override fun log(message: String) {
                                println(message)
                            }
                        }
                    }
                }
            )
        }
    }

}