package es.rafapuig.pmdm.clean.dictionary.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Phonetic(
    val audio: String = "",
    val sourceUrl: String? = "",
    val text: String? = ""
)
