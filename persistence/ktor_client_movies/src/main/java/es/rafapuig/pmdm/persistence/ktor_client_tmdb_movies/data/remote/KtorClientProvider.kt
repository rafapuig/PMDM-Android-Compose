package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.BuildConfig


object KtorClientProvider {

    val client by lazy {
        KtorClientFactory.create(
            apiKey = BuildConfig.API_KEY,
            apiToken = BuildConfig.API_ACCESS_TOKEN,
            language = "es-ES"
        )
    }

}