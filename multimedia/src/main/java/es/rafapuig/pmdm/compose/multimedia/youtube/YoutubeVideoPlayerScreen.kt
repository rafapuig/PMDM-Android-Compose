package es.rafapuig.pmdm.compose.multimedia.youtube

import android.app.Activity
import android.content.res.Configuration
import android.view.Window
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import es.rafapuig.pmdm.compose.multimedia.components.YoutubePlayerHost


fun setImmersiveMode(window: Window, enable: Boolean) {
    val controller = WindowCompat.getInsetsController(window, window.decorView)
    if (enable) {
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    } else {
        controller.show(WindowInsetsCompat.Type.systemBars())
    }
}

@Composable
fun YoutubeVideoPlayerScreen(modifier: Modifier = Modifier) {

    val viewModel: YoutubePlayerViewModel = viewModel()

    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val activity = LocalActivity.current as Activity

    LaunchedEffect(isLandscape) {
        setImmersiveMode(activity.window, isLandscape)
    }

    val youTubePlayerView =
        rememberYoutubePlayerController(viewModel.videoId)

    if (isLandscape) {
        LandscapeUI(youTubePlayerView)
    } else {
        PortraitUI(
            modifier = modifier,
            youTubePlayerView = youTubePlayerView,
            videoTitle = viewModel.videoTitle,
            onVideoIdChange = { viewModel.updateVideoId(it) }
        )
    }
}


@Composable
fun LandscapeUI(youTubePlayerView: YouTubePlayerView) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        YoutubePlayerHost(
            youTubePlayerView,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(16 / 9f)
        )
    }
}

@Composable
fun PortraitUI(
    modifier: Modifier,
    youTubePlayerView: YouTubePlayerView,
    videoTitle: String,
    onVideoIdChange: (String) -> Unit = {}
) {
    Column(modifier = modifier) {

        YoutubePlayerHost(
            youTubePlayerView,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Text(videoTitle)
        Button({ onVideoIdChange("FEtd5nA30HQ") }) {
            Text("Himmo de RDA")

        }
        Button({ onVideoIdChange("RclS0zqexxM") }) {
            Text("Himno de Rusia")
        }
        Button({ onVideoIdChange("-upuF7pfpEA") }) {
            Text("Himno de la Comunidad Valenciana")
        }
    }
}