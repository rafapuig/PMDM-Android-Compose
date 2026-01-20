package es.rafapuig.pmdm.client.todolist.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Factoría de objetos HttpClient de Ktor para realizar solicitudes HTTP
 * al API de Todos
 */
object KtorClientFactory {

    const val API_HOST = "ktor-todos.fly.dev"

    fun create(): HttpClient = HttpClient(Android) {

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
            }
        }

        install(Resources)

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