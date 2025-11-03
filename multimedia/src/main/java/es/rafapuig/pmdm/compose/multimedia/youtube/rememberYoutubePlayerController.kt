package es.rafapuig.pmdm.compose.multimedia.youtube

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun rememberYoutubePlayerController(
    videoId: String
): YouTubePlayerView {

    val context = LocalContext.current.applicationContext
    val playerView = remember { YouTubePlayerView(context) }

    var player by remember { mutableStateOf<YouTubePlayer?>(null) }

    // 🧠 Mapa que guarda el tiempo de cada video
    val videoPositions = remember { mutableStateMapOf<String, Float>() }


    // ⏱️ Guardar tiempo periódicamente
    DisposableEffect(player, videoId) {
        val listener = object : AbstractYouTubePlayerListener() {
            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                videoPositions[videoId] = second
            }
        }

        player?.addListener(listener)

        onDispose {
            player?.removeListener(listener)
        }
    }

    // 👇 Determinar desde dónde arrancar este video
    val startTime = videoPositions[videoId] ?: 0f

    // Solo una vez
    LaunchedEffect(Unit) {
        playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                player = youTubePlayer
                youTubePlayer.loadVideo(videoId, startTime)
            }
        })
    }

    // Cuando cambia el video
    LaunchedEffect(videoId) {
        player?.loadVideo(videoId, startTime)
    }

    return playerView
}
