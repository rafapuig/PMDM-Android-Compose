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

/**
 * Factoria de objetos HttpClient de Ktor para la realización de solicitudes HTTP
 * al API de The Movie Database
 */
object KtorClientFactory {

    fun create(
        apiKey: String = "",
        apiToken: String = "",
        language: String = "",
    ): HttpClient = HttpClient(Android) {

        /**
         * Añadimos el plugin para la serialización de JSON
         * (Usamos el serializador de kotlinx.serialization)
         */
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

                    /** Idioma en el que queremos las respuestas del API */
                    if (language.isNotEmpty())
                        append("language", language)

                    /**
                     * Usamos el API Key si se nos ha proporcionado esta
                     * y no se ha proporcionado el access token de la API.
                     */
                    if (apiKey.isNotEmpty() && apiToken.isEmpty()) {
                        append("api_key", apiKey)
                    }

                }

                /**
                 * Si se ha proporcionado un Access Token,
                 * lo añadimos una cabecera de autorización con el token
                 * por defecto en cada solicitud
                 */
                if (apiToken.isNotEmpty()) {
                    /** Añadir el Access Token a las cabeceras de cada solicitud */
                    headers.append(
                        "Authorization",
                        "Bearer ${apiToken}"
                    )
                }
            }
        }

        /**
         * Añadimos el plugin para el logging de las solicitudes
         * y respuestas
         */
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