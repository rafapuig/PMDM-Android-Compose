package es.rafapuig.pmdm.clean.dictionary.core.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeaningDto(
    @SerialName("partOfSpeech")
    val partOfSpeech: String = "",
    @SerialName("definitions")
    val definitions: List<DefinitionDto> = listOf(),
    @SerialName("synonyms")
    val synonyms: List<String> = listOf(),
    @SerialName("antonyms")
    val antonyms: List<String> = listOf()
)