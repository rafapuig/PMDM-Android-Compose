package es.rafapuig.pmdm.compose.multimedia.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayerHost(
    playerView: YouTubePlayerView,
    modifier: Modifier = Modifier,
    /*videoId: String  = "",
    initialSecond: Float = 0f,
    onCurrentSecond: (Float) -> Unit = {}*/

) {
    /*var player: YouTubePlayer? by remember { mutableStateOf(null) }

    // ✅ Inicializa solo una vez
    LaunchedEffect(Unit) {
        playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                player = youTubePlayer
                youTubePlayer.loadVideo(videoId, initialSecond)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                onCurrentSecond(second)
            }
        })
    }

    // ✅ Cuando el videoId cambie, cargar el nuevo video
    LaunchedEffect(videoId) {
        player?.loadVideo(videoId, initialSecond)
        // si quieres mantener el tiempo anterior: player?.loadVideo(videoId, lastPosition)
    }*/
    AndroidView(
        modifier = modifier,
        factory = { playerView },
        update = { },
    )
}
