package es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageInfoDto(
    @SerialName("id") val id: Int,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("url") val url: String,
    @SerialName("author") val author: String
)
