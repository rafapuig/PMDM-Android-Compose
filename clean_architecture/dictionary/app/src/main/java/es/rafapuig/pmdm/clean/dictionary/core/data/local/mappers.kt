package es.rafapuig.pmdm.clean.dictionary.core.data.local

import es.rafapuig.pmdm.clean.dictionary.core.data.local.entity.WordInfoEntity
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.WordInfo

fun WordInfoEntity.toDomain() =
    WordInfo(
        word = word,
        phonetics = phonetics,
        meanings = meanings,
        sourceUrls = sourceUrls
    )

