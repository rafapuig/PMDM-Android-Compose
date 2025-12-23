package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }

    private val contentType = "application/json".toMediaType()


    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/rafapuig/PMDM-Android-Compose/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()


    val apiService: TodosApiService by lazy {
        retrofit.create(TodosApiService::class.java)
    }

}