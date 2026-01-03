package es.rafapuig.pmdm.clean.dictionary.core.data.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class StringListConverter {

    private val jsonSerializer = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return jsonSerializer.encodeToString(list)
    }

    @TypeConverter
    fun toStringList(json: String): List<String> {
        return if (json.isBlank()) emptyList()
        else jsonSerializer.decodeFromString(json)
    }
}