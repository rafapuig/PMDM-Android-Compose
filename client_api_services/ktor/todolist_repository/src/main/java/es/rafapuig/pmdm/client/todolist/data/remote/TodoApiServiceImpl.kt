package es.rafapuig.pmdm.client.todolist.data.remote

import es.rafapuig.pmdm.client.todolist.data.remote.dto.CreateTodoRequest
import es.rafapuig.pmdm.client.todolist.data.remote.dto.TodoCompletedPatch
import es.rafapuig.pmdm.client.todolist.data.remote.dto.TodoDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TodoApiServiceImpl(
    private val client: HttpClient
) : TodoApiService {

    override suspend fun fetchAllTodos(): List<TodoDto> =
        client.get(TodoApiService.HttpRoutes.ALL_TODOS_ENDPOINT)
            .body()

    override suspend fun fetchTodoById(id: Int): TodoDto =
        client.get(TodoApiService.HttpRoutes.TODO_BY_ID_ENDPOINT) {
            parameter("id", id)
        }.body()


    override suspend fun addTodo(todo: CreateTodoRequest): TodoDto =
        client.post(TodoApiService.HttpRoutes.ADD_TODO_ENDPOINT) {
            contentType(ContentType.Application.Json)
            setBody(todo)
        }.body()


    override suspend fun updateTodo(id: Int, todo: TodoDto): TodoDto =
        client.put(TodoApiService.HttpRoutes.UPDATE_TODO_ENDPOINT) {
            parameter("id", id)
            contentType(ContentType.Application.Json)
            setBody(todo)
        }.body()


    override suspend fun setTodoDone(id: Int, patch: TodoCompletedPatch): TodoDto =
        client.patch(TodoApiService.HttpRoutes.SET_TODO_DONE_ENDPOINT) {
            parameter("id", id)
            contentType(ContentType.Application.Json)
            setBody(patch)
        }.body()

    override suspend fun deleteTodo(id: Int) {
        client.delete(TodoApiService.HttpRoutes.DELETE_TODO_ENDPOINT) {
            parameter("id", id)
        }
    }
}
