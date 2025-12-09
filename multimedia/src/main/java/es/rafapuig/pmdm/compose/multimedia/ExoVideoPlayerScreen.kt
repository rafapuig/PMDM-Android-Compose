package es.rafapuig.pmdm.compose.multimedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.ui.PlayerView

@Composable
fun ExoVideoPlayerScreen(
    url: String,
    viewModel: ExoVideoPlayerViewModel = viewModel())
{
    val context = LocalContext.current

    // Inicializamos una sola vez
    LaunchedEffect(Unit) {
        viewModel.initPlayer(context, url)
    }

    if(viewModel.isInitialized) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Black)
            ,
            factory = {
                PlayerView(context).apply {
                    player = viewModel.exoPlayer
                    useController = true
                }
            }
        )
    }
}
