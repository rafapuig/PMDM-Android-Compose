package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote

import java.util.Locale

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