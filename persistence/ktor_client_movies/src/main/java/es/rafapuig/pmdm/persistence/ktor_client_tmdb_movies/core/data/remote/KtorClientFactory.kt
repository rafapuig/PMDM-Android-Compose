package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.TMDBApiService.HttpRoutes.API_HOST
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.TMDBApiService.HttpRoutes.API_VERSION_PATH
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientFactory {

    fun create(
        apiKey: String = "",
        apiToken: String = "",
        language: String = "",
    ): HttpClient = HttpClient(Android) {

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        /**
         * Configuración de la solicitud por defecto para todas las solicitudes
         * realizadas mendiante este cliente HTTP.
         * Indicamos el host, el path y el protocolo.
         * Y añadimos los parámetros de idioma
         * y una cabecera para el token de la API.
         *
         * De esta manera ya no hará falta especificarlas en cada solicitud
         */
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = API_HOST
                encodedPath = "/$API_VERSION_PATH/"
                parameters.apply {
                    //append("api_key", apiKey)
                    append("language", language)
                }
                headers.append(
                    "Authorization",
                    "Bearer ${apiToken}"
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

}