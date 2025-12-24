package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote

import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.CreateTodoRequest
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.TodoCompletedPatch
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.TodoDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodosApiService {

    @GET("todos")
    suspend fun fetchAllTodos(): List<TodoDto>

    @GET("todos/{id}")
    suspend fun fetchTodoById(@Path("id") id: Int): TodoDto

    @POST("todos")
    suspend fun addTodo(@Body todo: CreateTodoRequest): TodoDto

    @PUT("todos/{id}")
    suspend fun updateTodo(@Path("id") id: Int, @Body todo: TodoDto): TodoDto

    @PATCH("todos/{id}")
    suspend fun setTodoDone(@Path("id") id: Int, @Body patch: TodoCompletedPatch): TodoDto

    @DELETE("todos/{id}")
    suspend fun deleteTodo(@Path("id") id: Int)

}