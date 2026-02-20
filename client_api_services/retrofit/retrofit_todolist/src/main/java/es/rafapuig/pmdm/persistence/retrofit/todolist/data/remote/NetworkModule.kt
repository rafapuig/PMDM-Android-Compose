package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {

    const val BASE_URL = "https://ktor-todos.fly.dev/" //  "http://192.168.1.8:8080/" //"https://my-json-server.typicode.com/rafapuig/PMDM-Android-Compose/"

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }

    private val contentType = "application/json;charset=UTF-8".toMediaType()


    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()


    val apiService: TodosApiService by lazy {
        retrofit.create(TodosApiService::class.java)
    }

}