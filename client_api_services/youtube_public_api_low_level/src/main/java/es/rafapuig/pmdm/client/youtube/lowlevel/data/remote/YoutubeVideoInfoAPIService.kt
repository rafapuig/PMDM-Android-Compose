package es.rafapuig.pmdm.client.youtube.lowlevel.data.remote

import es.rafapuig.pmdm.client.youtube.lowlevel.data.remote.dto.VideoInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class YoutubeVideoInfoAPIService {

    suspend fun getVideoInfo(videoId: String): VideoInfoDto =
        withContext(Dispatchers.IO) {
            val url = buildUrl(videoId)
            val responseBody = YoutubePublicAPIClient.call(url)
            parseResponse(responseBody).copy(id = videoId)
        }
}

private fun buildUrl(videoId: String): URL {
    return URL("${YoutubePublicAPIClient.BASE_URL}$videoId&format=json")
}

private fun parseResponse(json: String): VideoInfoDto {
    return VideoJsonSerializer.fromJson(json)
}