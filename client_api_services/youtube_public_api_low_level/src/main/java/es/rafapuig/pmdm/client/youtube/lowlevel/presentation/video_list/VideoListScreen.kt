package es.rafapuig.pmdm.client.youtube.lowlevel.presentation.video_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import es.rafapuig.pmdm.client.youtube.lowlevel.domain.model.VideoInfo
import es.rafapuig.pmdm.client.youtube.lowlevel.presentation.video_list.components.VideoInfoItem

@Composable
fun VideoListScreen(
    videoInfos : List<VideoInfo>,
    onVideoClick: (String) -> Unit
) {
    LazyColumn {
        items(videoInfos) { videoInfo ->
            VideoInfoItem(
                videoInfo = videoInfo,
                onVideoClick = onVideoClick
            )
        }
    }
}

@Composable
fun VideoListScreenPreview() {
    VideoListScreen(
        videoInfos = listOf(
            VideoInfo(
                id = "1",
                title = "Video 1"
            )
        ),
        onVideoClick = {}
    )
}