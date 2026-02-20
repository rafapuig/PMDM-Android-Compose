package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.util

import java.util.Locale

/**
 * Obtiene el idioma del dispositivo en formato para la llamada a la API
 */
fun getDeviceLanguage(): String {
    val locale = Locale.getDefault()

    val language = locale.language      // es, en, fr
    val country = locale.country        // ES, US, FR

    return if (country.isNotEmpty()) {
        "$language-$country"
    } else {
        language
    }
}