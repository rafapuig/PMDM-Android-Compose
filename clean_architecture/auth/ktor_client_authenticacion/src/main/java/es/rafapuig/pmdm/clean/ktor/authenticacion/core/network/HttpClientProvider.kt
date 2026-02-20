package es.rafapuig.pmdm.clean.ktor.authenticacion.core.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.resources.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object HttpClientProvider {
    fun createClient(): HttpClient = HttpClient(CIO) {
        install(Resources)
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
    }
}
