package es.rafapuig.pmdm.clean.dictionary.core.data.util

interface JsonParser {

    fun <T> fromJson(json: String, type: Class<T>): T?

    fun <T> toJson(obj: T, type: Class<T>): String?

}
