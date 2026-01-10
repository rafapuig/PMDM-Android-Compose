package es.rafapuig.pmdm.client.youtube.lowlevel

import android.app.Application
import es.rafapuig.pmdm.client.youtube.lowlevel.data.VideoInfoRepositoryImpl
import es.rafapuig.pmdm.client.youtube.lowlevel.data.remote.YoutubeVideoInfoAPIService
import es.rafapuig.pmdm.client.youtube.lowlevel.domain.repository.VideoInfoRepository

class YoutubeVideoInfo : Application() {

    val videoInfoRepository: VideoInfoRepository by lazy {
        VideoInfoRepositoryImpl(
            apiService = YoutubeVideoInfoAPIService()
        )
    }
}