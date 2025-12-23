package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote

import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.TodoDto
import retrofit2.http.GET

interface TodosApiService {
    @GET("todos")
    suspend fun fetchAllTodos(): List<TodoDto>
}