package es.rafapuig.pmdm.client.youtube.lowlevel.presentation.video_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun VideoListScreenRoot(
    viewModel: VideoListViewModel = viewModel(factory = VideoListViewModel.Factory)
) {
    val videoInfos by viewModel.videoInfos.collectAsStateWithLifecycle()
    VideoListScreen(
        videoInfos = videoInfos,
        onVideoClick = {}
    )
}