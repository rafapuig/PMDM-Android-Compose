package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.dto.MoviesResponse

interface TMDBApiService {

    object HttpRoutes {
        const val API_HOST = "api.themoviedb.org"
        const val API_VERSION_PATH = "3"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val POPULAR_MOVIES_ENDPOINT = "movie/popular"
        const val TOP_RATED_MOVIES_ENDPOINT = "movie/top_rated"
        const val SEARCH_MOVIES_ENDPOINT = "search/movie"
    }

    suspend fun fetchPopularMovies(page: Int = 1, region: String? = "ES"): MoviesResponse

    suspend fun fetchTopRatedMovies(page: Int = 1, region: String? = "ES"): MoviesResponse

    suspend fun searchMovies(query: String, page: Int = 1): MoviesResponse


}