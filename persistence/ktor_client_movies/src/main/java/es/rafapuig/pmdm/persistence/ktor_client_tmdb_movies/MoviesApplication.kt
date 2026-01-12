package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies

import android.app.Application
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.KtorClientFactory
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.TMDBApiServiceImpl
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.repositories.MoviesRepositoryRemoteImpl
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.repository.MoviesRepository

class MoviesApplication : Application() {

    val moviesRepository : MoviesRepository by lazy {

        val apiKey = BuildConfig.API_KEY
        val apiToken = BuildConfig.API_ACCESS_TOKEN
        val language = "es-ES" // getDeviceLanguage()

        val client = KtorClientFactory.create(apiKey, apiToken, language)
        val apiService = TMDBApiServiceImpl(client)
        MoviesRepositoryRemoteImpl(apiService)
    }

}