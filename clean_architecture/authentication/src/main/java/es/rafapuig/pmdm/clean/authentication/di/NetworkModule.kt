package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.AuthApi
import es.rafapuig.pmdm.clean.authentication.core.network.AuthInterceptor
import es.rafapuig.pmdm.clean.authentication.core.network.json
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {

    single {
        json
    }

    single {
        AuthInterceptor(get())
    }

    // Para inyectar la dependencia de un OkHttpClient como un singleton
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }


    single {
        Retrofit.Builder()
            .baseUrl("https://api.tuapp.com/")
            .client(get()) // Aqui se usa el OkHttpClient inyectado
            .addConverterFactory(
                get<Json>().asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }

    single<AuthApi> {
        get<Retrofit>().create(AuthApi::class.java)
    }
}
