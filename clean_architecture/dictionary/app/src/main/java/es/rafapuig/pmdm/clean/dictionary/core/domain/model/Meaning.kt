package es.rafapuig.pmdm.clean.dictionary.core.domain.model

import es.rafapuig.pmdm.clean.dictionary.core.data.remote.dto.DefinitionDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meaning(
    val partOfSpeech: String = "",
    val definitions: List<Definition> = listOf(),
    val synonyms: List<String> = listOf(),
    val antonyms: List<String> = listOf()
)
