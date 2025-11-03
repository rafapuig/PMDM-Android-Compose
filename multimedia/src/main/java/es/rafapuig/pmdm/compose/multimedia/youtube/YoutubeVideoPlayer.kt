package es.rafapuig.pmdm.compose.multimedia.youtube

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubeVideoPlayer(
    videoId: String,
    modifier: Modifier = Modifier,
    initialSecond: Float = 0f,
    isLandscape: Boolean = false,
    onCurrentSecond: (Float) -> Unit = {}
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    var player: YouTubePlayer? by remember { mutableStateOf(null) }
    var lastVideoId by remember { mutableStateOf<String?>(null) }
    var ready by remember { mutableStateOf(false) }

    // ⬇️ Se ejecuta SOLO cuando el videoId cambia
    /*LaunchedEffect(videoId, ready) {
        if (ready && lastVideoId != videoId) {
            lastVideoId = videoId
            player?.loadVideo(videoId, initialSecond)
        }
    }*/

    val context = LocalContext.current as Activity

    val youTubePlayerView = remember {
        YouTubePlayerView(context).apply {

        }
    }

    LaunchedEffect(Unit) {

        lifecycleOwner.lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                player = youTubePlayer
                youTubePlayer.loadVideo(videoId, initialSecond)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                onCurrentSecond(second)
            }

            override fun onError(
                youTubePlayer: YouTubePlayer,
                error: PlayerConstants.PlayerError
            ) {
                Log.e("YoutubeVideoPlayer", "Error: ${error.name}")
            }
        })
    }

    // Sólo reaccionar si el player ya existe (evita bucles)
    LaunchedEffect(videoId) {
        player?.let {
            val pos = initialSecond
            it.loadVideo(videoId, initialSecond)
        }
    }

    /*DisposableEffect(Unit) {

        youTubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                player = youTubePlayer
                lastVideoId = videoId
                ready = true
                youTubePlayer.loadVideo(videoId, initialSecond)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                onCurrentSecond(second)
            }

            override fun onError(
                youTubePlayer: YouTubePlayer,
                error: PlayerConstants.PlayerError
            ) {
                Log.e("YoutubeVideoPlayer", "Error: ${error.name}")
            }
        })

        onDispose {
            // ❗ No destruir player al rotar
        }
    }*/


    AndroidView(
        /*modifier =
        if (isLandscape) Modifier
            .fillMaxHeight()
            .aspectRatio(16 / 9f)
        else modifier
            .fillMaxWidth()
            .wrapContentHeight(),*/

        factory = { context -> youTubePlayerView },
        update = {}
    )
}


