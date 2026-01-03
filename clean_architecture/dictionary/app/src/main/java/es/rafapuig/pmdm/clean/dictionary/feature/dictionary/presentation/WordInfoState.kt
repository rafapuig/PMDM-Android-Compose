package es.rafapuig.pmdm.clean.dictionary.feature.dictionary.presentation

import es.rafapuig.pmdm.clean.dictionary.core.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false,
    val query: String = ""
)
