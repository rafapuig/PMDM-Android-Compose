package es.rafapuig.pmdm.clean.dictionary.core.domain.model

data class WordInfo(
    val word: String = "",
    val phonetics: List<Phonetic> = listOf(),
    val meanings: List<Meaning> = listOf(),
    val sourceUrls: List<String> = listOf()
)
