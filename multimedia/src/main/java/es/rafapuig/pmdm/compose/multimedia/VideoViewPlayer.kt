package es.rafapuig.pmdm.compose.multimedia

import android.net.Uri
import android.util.Log
import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import es.rafapuig.pmdm.compose.multimedia.ui.theme.MultimediaTheme

@Composable
fun VideoViewPlayer(url: String) {
    AndroidView(
        factory = { context ->
            VideoView(context).apply {
                setVideoURI(Uri.parse(url))
                setOnPreparedListener { start() }
                setOnErrorListener { _, what, extra ->
                    Log.e("VideoView", "Error: $what, $extra")
                    false
                }
            }
        },
        update = { videoView ->
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun VideoViewPlayerPreview() {
    MultimediaTheme {
        VideoViewPlayer("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
    }
}