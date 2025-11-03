package es.rafapuig.pmdm.compose.multimedia.youtube

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.multimedia.data.YoutubePublicAPIClient
import kotlinx.coroutines.launch

class YoutubePlayerViewModel : ViewModel() {

    val apiClient = YoutubePublicAPIClient()

    val currentSecond = mutableStateMapOf<String,Float>()

    var videoId by mutableStateOf("FEtd5nA30HQ")
    private set

    var videoTitle by mutableStateOf("")
    private set

    init {
        viewModelScope.launch {
            snapshotFlow { videoId }.collect {
                val video = apiClient.getVideoInfo(videoId)
                videoTitle = video.title
            }
        }
    }

    fun updateVideoId(newVideoId: String) {
        videoId = newVideoId
    }

    fun updateCurrentSecond(newSecond: Float) {
        currentSecond[videoId] = newSecond
    }
}
