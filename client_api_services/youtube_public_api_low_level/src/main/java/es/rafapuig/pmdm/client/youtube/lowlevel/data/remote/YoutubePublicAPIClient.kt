package es.rafapuig.pmdm.client.youtube.lowlevel.data.remote

import java.net.HttpURLConnection
import java.net.URL

object YoutubePublicAPIClient {

    const val BASE_URL = "https://www.youtube.com/oembed?url=https://www.youtube.com/watch?v="


    fun call(url : URL): String {
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"

        // Establece la conexión a la URL
        httpURLConnection.connect()
        // Obtiene el código de respuesta de la solicitud
        val responseCode = httpURLConnection.responseCode

        if (responseCode == HttpURLConnection.HTTP_OK) {
            val body = httpURLConnection.inputStream
                .bufferedReader()
                .useLines { lines ->
                lines.joinToString("\n")
            }
            return body
        } else {
            throw Exception("HTTP request failed with response code $responseCode")
        }
    }

}