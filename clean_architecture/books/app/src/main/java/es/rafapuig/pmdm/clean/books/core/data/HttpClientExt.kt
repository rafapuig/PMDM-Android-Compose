package es.rafapuig.pmdm.clean.books.core.data

import es.rafapuig.pmdm.clean.books.core.domain.DataError
import es.rafapuig.pmdm.clean.books.core.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

suspend inline fun <reified T> safeCall(
    crossinline execute: suspend () -> HttpResponse
): Result<T, DataError.Remote> =
    try {
        responseToResult(execute())
    } catch (e: SocketTimeoutException) {
        Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        Result.Failure(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        /**
         * CUIDADO! Estamos interceptando una Excepción General
         * Lo que incluye a la CancelationException lanzada para cancelar corrutinas
         * Tenemos que tener esto en cuenta cuando utilizamos funciones suspendidas
         * El tratamiento consiste en verificar si la corrutina sigue activa
         * y si no le está lanzamos una excepción, ese es el efecto de llamar a ensureActive()
         */
        currentCoroutineContext().ensureActive()
        Result.Failure(DataError.Remote.UNKNOWN)
    }

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> try {
            Result.Success(response.body<T>())
        } catch (e: NoTransformationFoundException) {
            Result.Failure(DataError.Remote.SERIALIZATION)
        }

        408 -> Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Failure(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Failure(DataError.Remote.SERVER)
        else -> Result.Failure(DataError.Remote.UNKNOWN)
    }

}