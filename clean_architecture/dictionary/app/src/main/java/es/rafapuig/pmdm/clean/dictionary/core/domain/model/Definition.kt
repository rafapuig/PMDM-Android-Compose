package es.rafapuig.pmdm.clean.dictionary.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Definition(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
)
