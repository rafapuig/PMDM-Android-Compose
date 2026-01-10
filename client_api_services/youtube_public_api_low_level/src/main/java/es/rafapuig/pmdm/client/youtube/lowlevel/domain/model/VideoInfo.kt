package es.rafapuig.pmdm.client.youtube.lowlevel.domain.model

data class VideoInfo(
    val id: String,
    val title: String,
    val authorName: String = "",
    val authorUrl: String = "",
    val thumbnailUrl: String = "",
)
