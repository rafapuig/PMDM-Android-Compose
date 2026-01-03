package es.rafapuig.pmdm.clean.dictionary.core.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefinitionDto(
    @SerialName("definition")
    val definition: String = "",
    @SerialName("synonyms")
    val synonyms: List<String> = listOf(),
    @SerialName("antonyms")
    val antonyms: List<String> = listOf(),
    @SerialName("example")
    val example: String? = null
)