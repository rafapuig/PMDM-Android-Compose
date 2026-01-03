package es.rafapuig.pmdm.clean.dictionary.core.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordInfoDto(
    @SerialName("word")
    val word: String = "",
    @SerialName("phonetics")
    val phonetics: List<PhoneticDto> = listOf(),
    @SerialName("meanings")
    val meanings: List<MeaningDto> = listOf(),
    @SerialName("sourceUrls")
    val sourceUrls: List<String> = listOf()
)