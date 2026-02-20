package es.rafapuig.pmdm.clean.authentication.core.network

import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = false
}
