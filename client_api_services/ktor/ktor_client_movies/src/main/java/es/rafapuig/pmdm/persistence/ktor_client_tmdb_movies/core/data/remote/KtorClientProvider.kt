package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.BuildConfig


/** Service Locator para proporcionar el cliente HTTP de Ktor */

object KtorClientProvider {

    var apiKey: String? = BuildConfig.API_KEY
    var apiToken: String? = BuildConfig.API_ACCESS_TOKEN
    var language: String = "es-ES"

    fun configure(
        apiKey: String? = null,
        apiToken: String? = null,
        language: String = ""
    ) {
        this.apiKey = apiKey
        this.apiToken = apiToken
        this.language = language
    }

    val client by lazy {
        KtorClientFactory.create(
            apiKey = apiKey ?: "",
            apiToken = apiToken ?: "",
            language = language
        )
    }

}