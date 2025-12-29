package es.rafapuig.pmdm.persistence.ktor_client_popular_movies

import android.app.Application
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.KtorClientFactory
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.KtorClientProvider
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.MoviesRepositoryRemoteImpl
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiServiceImpl

class MoviesApplication : Application() {

    val moviesRepository by lazy {

        val apiKey = BuildConfig.API_KEY
        val apiToken = BuildConfig.API_ACCESS_TOKEN
        val language = "es-ES" // getDeviceLanguage()

        val client = KtorClientFactory.create(apiKey, apiToken, language)
        val apiService = TMDBApiServiceImpl(client)
        MoviesRepositoryRemoteImpl(apiService)
    }

}