package es.rafapuig.pmdm.clean.dictionary.core.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Meaning
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Phonetic
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class MeaningList(val items: List<Meaning>)

@ProvidedTypeConverter
class Converters(
    //private val jsonParser: JsonParser,
    private val jsonSerializer: Json
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
        return jsonSerializer.decodeFromString<List<Meaning>>(json)
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return jsonSerializer.encodeToString(meanings)
    }

    @TypeConverter
    fun fromPhoneticsJson(json: String): List<Phonetic> {
        return jsonSerializer.decodeFromString(json)
    }

    @TypeConverter
    fun toPhoneticsJson(phonetics: List<Phonetic>): String {
        return jsonSerializer.encodeToString(phonetics)
    }

}