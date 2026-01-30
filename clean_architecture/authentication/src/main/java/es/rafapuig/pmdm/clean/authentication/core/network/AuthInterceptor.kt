package es.rafapuig.pmdm.clean.authentication.core.network

import es.rafapuig.pmdm.clean.authentication.auth.data.local.AuthLocalDataSource
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.LogoutUseCase
import es.rafapuig.pmdm.clean.authentication.core.presentation.SessionManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val local: AuthLocalDataSource,
    //private val logoutUseCase: LogoutUseCase
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = runBlocking {
            local.getToken().first()
        }

        val request = chain.request().newBuilder().apply {
            token?.let {
                addHeader("Authorization", "Bearer $it")
            }
        }.build()

        val response = chain.proceed(request)

       /* if(response.code == 401) {
            runBlocking {
                logoutUseCase()
            }
        }*/

        return response
    }
}