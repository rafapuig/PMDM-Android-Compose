package es.rafapuig.pmdm.compose.multimedia.youtube

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun rememberYoutubePlayerController(
    videoId: String
): YouTubePlayerView {

    val context = LocalContext.current.applicationContext

    // Crear el YouTubePlayerView una vez recordarlo
    val playerView = remember {
        YouTubePlayerView(context).apply {
            enableAutomaticInitialization = false
        }
    }

    // 🧠 Mapa que guarda el tiempo de cada video
    val videoPositions = remember { mutableStateMapOf<String, Float>() }

    // 👇 Determinar desde dónde arrancar este video
    val startTime =videoPositions[videoId] ?: 0f


    playerView.enableAutomaticInitialization = true

    // 🧠 Guardar el reproductor
    var player by remember { mutableStateOf<YouTubePlayer?>(null) }

    // ⏱️ Guardar tiempo periódicamente
    DisposableEffect(videoId) {

        val listener =
            object : AbstractYouTubePlayerListener() {
                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                    videoPositions[videoId] = second
                }
            }

        // Si no hay player no hace nada
        player?.addListener(listener) // Subcribirse un nuevo listener ahora para el nuevo video


        onDispose {
            player?.removeListener( listener) // Desregistrar el antiguo
        }
    }

    // Solamente se ejecuta la primera vez
    LaunchedEffect(Unit) {

        val callback = object : YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                Log.i("YoutubePlayerController", "Player is ready")
                player = youTubePlayer
                // mas rapido que esperar a que se ejecute el LaunchEffect
                youTubePlayer.loadVideo(videoId, startTime)

                //val default = DefaultPlayerUiController(youTubePlayer)
                //playerView.setCustomPlayerUi()
            }
        }

        playerView.getYouTubePlayerWhenReady(callback)
    }

    /*LaunchedEffect(player) {
        player?.loadVideo(videoId = videoId, startSeconds = startTime)
    }*/

    /**
     * Si cambia el video
     * La primera vez no hay player (null)
     * La segunda vez si hay player y se cambia de video,
     * primer se pone en cola y se busca la posición de inicio
     * se espera 250ms para evitar glitch y a continuacion se inicia la reproducción
     *
     * Lo hacemos en un LaunchedEffect para disponer de una corrutina y esperar el tiempo
     * (en DisposableEffect no se ejecuta en una corrutina)
     */


    LaunchedEffect( videoId) {
        //Log.i("YoutubePlayerController", "Loading video $videoId in player $player")
        player?.cueVideo(videoId = videoId, startSeconds = startTime)
        player?.seekTo(startTime)
        delay(250)
        player?.play()
    }

    return playerView
}
