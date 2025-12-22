package es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.data

import org.json.JSONArray
import org.json.JSONObject

fun <T> JSONArray.parseList(parse: (JSONObject) -> T) =
    buildList {
        for (i in 0 until length()) {
            val obj = getJSONObject(i)
            val element = parse(obj)
            add(element)
        }
    }


inline fun <reified T> JSONObject.parseField(field: String) : T =
    when (T::class) {
        String::class -> getString(field) as T
        Int::class -> getInt(field) as T
        Long::class -> getLong(field) as T
        Double::class -> getDouble(field) as T
        Boolean::class -> getBoolean(field) as T
        else -> throw IllegalArgumentException("Unsupported type")
    }