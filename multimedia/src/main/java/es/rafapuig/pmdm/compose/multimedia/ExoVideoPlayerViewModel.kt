package es.rafapuig.pmdm.compose.multimedia

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class ExoVideoPlayerViewModel : ViewModel() {

    lateinit var exoPlayer: ExoPlayer

    var isInitialized by mutableStateOf(false)

    fun initPlayer(context: android.content.Context, url: String) {
        if (!isInitialized) {
            exoPlayer = ExoPlayer.Builder(context).build().apply {
                setMediaItem(MediaItem.fromUri(url))
                prepare()
                playWhenReady = true
            }
            isInitialized = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (isInitialized) {
            exoPlayer.release()
        }
    }
}