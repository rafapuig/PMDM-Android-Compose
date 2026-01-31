package es.rafapuig.pmdm.clean.authentication.core.data.remote.dto

import kotlinx.serialization.json.Json
import retrofit2.Response

inline fun <reified E> Response<*>.parseApiError(json: Json): E? {

    val errorBody = errorBody() ?: return null

    return json.decodeFromString(errorBody.string())
}