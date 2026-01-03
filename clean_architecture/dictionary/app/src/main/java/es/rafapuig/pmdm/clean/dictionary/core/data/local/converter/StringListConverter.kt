package es.rafapuig.pmdm.clean.dictionary.core.data.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class StringListConverter {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return json.encodeToString(list)
    }

    @TypeConverter
    fun toStringList(jsonString: String): List<String> {
        return if (jsonString.isBlank()) emptyList()
        else json.decodeFromString(jsonString)
    }
}