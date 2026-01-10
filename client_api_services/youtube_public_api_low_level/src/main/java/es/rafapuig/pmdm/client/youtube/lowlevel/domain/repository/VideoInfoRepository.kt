package es.rafapuig.pmdm.client.youtube.lowlevel.domain.repository

import es.rafapuig.pmdm.client.youtube.lowlevel.domain.model.VideoInfo
import kotlinx.coroutines.flow.Flow

interface VideoInfoRepository {

    suspend fun getVideoInfo(videoId: String): VideoInfo

    fun getVideoInfos(videoIds: List<String>): Flow<List<VideoInfo>>

}
