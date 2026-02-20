package es.rafapuig.pmdm.apiservices.ktor.themealdb.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun buildHttpClient(engine: HttpClientEngine = CIO.create()) = HttpClient(engine) {

    install(ContentNegotiation) {
        json(
            json = Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
    }

    defaultRequest {
        contentType(ContentType.Application.Json)
        parameters {
            //append("api_key", "YOUR_API_KEY")
        }
    }

    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                println("Ktor-logger: $message")
            }
        }
    }

}
