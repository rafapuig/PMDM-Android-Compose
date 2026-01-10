package es.rafapuig.pmdm.client.youtube.lowlevel.data

import es.rafapuig.pmdm.client.youtube.lowlevel.data.remote.YoutubeVideoInfoAPIService
import es.rafapuig.pmdm.client.youtube.lowlevel.data.remote.toVideoInfo
import es.rafapuig.pmdm.client.youtube.lowlevel.domain.model.VideoInfo
import es.rafapuig.pmdm.client.youtube.lowlevel.domain.repository.VideoInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoInfoRepositoryImpl(
    private val apiService: YoutubeVideoInfoAPIService
) : VideoInfoRepository {

    override suspend fun getVideoInfo(videoId: String): VideoInfo {
        return apiService.getVideoInfo(videoId).toVideoInfo()
    }

    override fun getVideoInfos(videoIds: List<String>): Flow<List<VideoInfo>> = flow {
        emit(
            videoIds.map { videoId ->
                apiService.getVideoInfo(videoId).toVideoInfo()
            }
        )
    }
}


