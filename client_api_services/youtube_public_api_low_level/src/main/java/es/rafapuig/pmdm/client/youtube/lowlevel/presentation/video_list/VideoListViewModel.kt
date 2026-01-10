package es.rafapuig.pmdm.client.youtube.lowlevel.presentation.video_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.client.youtube.lowlevel.YoutubeVideoInfo
import es.rafapuig.pmdm.client.youtube.lowlevel.data.VideoInfoRepositoryImpl
import es.rafapuig.pmdm.client.youtube.lowlevel.data.remote.YoutubeVideoInfoAPIService
import es.rafapuig.pmdm.client.youtube.lowlevel.domain.model.sampleVideoIds
import es.rafapuig.pmdm.client.youtube.lowlevel.domain.repository.VideoInfoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class VideoListViewModel(
    private val videoInfoRepository: VideoInfoRepository
) : ViewModel() {

    val videoInfos =
        videoInfoRepository.getVideoInfos(sampleVideoIds).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as YoutubeVideoInfo
                VideoListViewModel(
                    videoInfoRepository = app.videoInfoRepository
                )
            }
        }
    }

}