package es.rafapuig.pmdm.clean.dictionary.core.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import es.rafapuig.pmdm.clean.dictionary.core.data.util.JsonParser
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Meaning
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Phonetic
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class MeaningList(val items: List<Meaning>)

@ProvidedTypeConverter
class Converters(
    //private val jsonParser: JsonParser,
    private val json: Json
) {
    /*@TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson(
            json,
            MeaningList::class.java
        )?.items ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            MeaningList(meanings),
            MeaningList::class.java
        ) ?: "[]"
    }*/

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return this.json.decodeFromString<List<Meaning>>(json)
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return this.json.encodeToString(meanings)
    }

    @TypeConverter
    fun fromPhoneticsJson(json: String): List<Phonetic> {
        return this.json.decodeFromString(json)
    }

    @TypeConverter
    fun toPhoneticsJson(phonetics: List<Phonetic>): String {
        return this.json.encodeToString(phonetics)
    }

}