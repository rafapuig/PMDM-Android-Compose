package es.rafapuig.pmdm.client.youtube.lowlevel.data.remote.dto

data class VideoInfoDto(
    val id: String,
    val title: String,
    val author_name: String,
    val author_url: String,
    val thumbnail_url: String,
    val thumbnail_width: Int,
    val thumbnail_height: Int
)
