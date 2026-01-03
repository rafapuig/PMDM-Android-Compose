package es.rafapuig.pmdm.clean.dictionary.core.data.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


class KotlinSerializationJsonParser(
    val json: Json
) : JsonParser {

    @Suppress("UNCHECKED_CAST")
    override fun <T> fromJson(json: String, type: Class<T>): T? {
        val serializer: KSerializer<T> = serializer(type) as KSerializer<T>
        return this.json.decodeFromString(serializer,json)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> toJson(obj: T, type: Class<T>): String? {
        val serializer = serializer(type) as KSerializer<T>
        return json.encodeToString(serializer, obj)
    }

    inline fun <reified T> fromJson(json: String) : T?  {
        //val serializer = serializer<T>()
        return this.json.decodeFromString<T>( json)
    }

}