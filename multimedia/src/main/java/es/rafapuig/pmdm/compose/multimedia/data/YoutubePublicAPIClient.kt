package es.rafapuig.pmdm.compose.multimedia.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class YoutubePublicAPIClient() {

    suspend fun getVideoInfo(videoId: String): YoutubeVideoDto {
        return withContext(Dispatchers.IO) {
            val json = call(videoId)
            parseResponse(json).copy(id = videoId)
        }
    }

    companion object {
        const val BASE_URL = "https://www.youtube.com/oembed?url=https://www.youtube.com/watch?v="

        private fun buildUrl(videoId: String): URL {
            return URL("$BASE_URL$videoId&format=json")
        }

        private fun call(videoId: String): String {
            val url = buildUrl(videoId)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connect()
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return httpURLConnection.inputStream.bufferedReader().useLines { lines ->
                    lines.joinToString("\n")
                }
            } else {
                throw Exception("HTTP request failed with response code $responseCode")
            }
        }

        private fun parseResponse(json: String): YoutubeVideoDto {
            val jsonObject = JSONObject(json)
            return YoutubeVideoDto(
                id="",
                title = jsonObject.getString("title"),
                author = jsonObject.getString("author_name"),
            )
        }
    }
}