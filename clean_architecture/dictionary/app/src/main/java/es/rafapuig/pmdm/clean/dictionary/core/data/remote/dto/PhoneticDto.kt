package es.rafapuig.pmdm.clean.dictionary.core.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneticDto(
    @SerialName("audio")
    val audio: String = "",
    @SerialName("sourceUrl")
    val sourceUrl: String? = "",
    @SerialName("text")
    val text: String? = ""
)