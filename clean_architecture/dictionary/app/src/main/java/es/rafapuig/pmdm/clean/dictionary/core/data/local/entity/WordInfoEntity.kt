package es.rafapuig.pmdm.clean.dictionary.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Meaning
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Phonetic

@Entity(tableName = "word_info")
data class WordInfoEntity(
    @PrimaryKey val id: Int? = null,
    val word: String = "",
    val phonetics: List<Phonetic> = listOf(),
    val meanings: List<Meaning> = listOf(),
    val sourceUrls: List<String> = listOf()
)
