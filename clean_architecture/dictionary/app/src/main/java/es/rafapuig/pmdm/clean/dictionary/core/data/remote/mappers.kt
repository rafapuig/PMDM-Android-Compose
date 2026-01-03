package es.rafapuig.pmdm.clean.dictionary.core.data.remote

import es.rafapuig.pmdm.clean.dictionary.core.data.local.entity.WordInfoEntity
import es.rafapuig.pmdm.clean.dictionary.core.data.remote.dto.DefinitionDto
import es.rafapuig.pmdm.clean.dictionary.core.data.remote.dto.MeaningDto
import es.rafapuig.pmdm.clean.dictionary.core.data.remote.dto.PhoneticDto
import es.rafapuig.pmdm.clean.dictionary.core.data.remote.dto.WordInfoDto
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Definition
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Meaning
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Phonetic
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.WordInfo


fun DefinitionDto.toDomain() =
    Definition(
        antonyms = antonyms,
        definition = definition,
        example = example,
        synonyms = synonyms
    )


fun MeaningDto.toDomain() =
    Meaning(
        partOfSpeech = partOfSpeech,
        definitions = definitions.map { it.toDomain() },
        synonyms = synonyms,
        antonyms = antonyms
    )

fun PhoneticDto.toDomain() =
    Phonetic(
        audio = audio,
        sourceUrl = sourceUrl,
        text = text
    )

fun WordInfoDto.toDomain() =
    WordInfo(
        word = word,
        phonetics = phonetics.map { it.toDomain() },
        meanings = meanings.map { it.toDomain() },
        sourceUrls = sourceUrls
    )

fun WordInfoDto.toDatabase() = WordInfoEntity(
    word = word,
    phonetics = phonetics.map { it.toDomain() },
    meanings = meanings.map { it.toDomain() },
    sourceUrls = sourceUrls
)





