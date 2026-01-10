package es.rafapuig.pmdm.client.youtube.lowlevel.presentation.video_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import es.rafapuig.pmdm.client.youtube.lowlevel.domain.model.VideoInfo

@Composable
fun VideoInfoItem(
    videoInfo: VideoInfo,
    onVideoClick: (String) -> Unit = {}
) {
    Column {
        AsyncImage(
            model = videoInfo.thumbnailUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Title: ${videoInfo.title}")
        Text(text = "Author: ${videoInfo.authorName}")
    }

}

@Preview
@Composable
fun VideoInfoItemPreview() {
    VideoInfoItem(
        videoInfo = VideoInfo(
            id = "WT9-4DXUqsM",
            title = "The Compose Multiplatform Crash Course for 2025 - Build a Clean Code Book App",
            authorName = "Philipp Lackner",
            thumbnailUrl = "https://i.ytimg.com/vi/WT9-4DXUqsM/hqdefault.jpg"
        )
    )
}