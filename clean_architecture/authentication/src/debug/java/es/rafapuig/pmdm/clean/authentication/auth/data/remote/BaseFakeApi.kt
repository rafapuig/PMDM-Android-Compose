package es.rafapuig.pmdm.clean.authentication.auth.data.remote

import kotlinx.coroutines.delay
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.io.IOException

abstract class BaseFakeApi(
    private val json: Json = Json { ignoreUnknownKeys = true },
    private val delayMs: Long = 300
) {

    protected suspend fun <T> success(data: T): Response<T> {
        delay(delayMs)
        return Response.success(data)
    }

    @OptIn(InternalSerializationApi::class)
    protected suspend fun <T,E> error(
        code: Int,
        serializer: KSerializer<E>,
        error: E
    ): Response<T> {
        delay(delayMs)

        val errorJson = json.encodeToString(
            serializer,
            error
        )

        return Response.error(
            code,
            errorJson.toErrorBody()
        )
    }

    protected suspend fun networkError(): Nothing {
        delay(delayMs)
        throw IOException("Fake network error")
    }


    fun String.toErrorBody(): ResponseBody =
        toResponseBody("application/json".toMediaType())
}
