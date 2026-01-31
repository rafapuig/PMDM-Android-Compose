package es.rafapuig.pmdm.client.todolist.data.remote

import es.rafapuig.pmdm.client.todolist.data.remote.dto.CreateTodoRequest
import es.rafapuig.pmdm.client.todolist.data.remote.dto.TodoCompletedPatch
import es.rafapuig.pmdm.client.todolist.data.remote.dto.TodoDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.delete
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.patch
import io.ktor.client.plugins.resources.post
import io.ktor.client.plugins.resources.put
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.resources.Resource


class TodoApiServiceUnsafeImpl(
    private val client: HttpClient
) : TodoApiService {

    override suspend fun fetchAllTodos(): List<TodoDto> =
        client.get {
            url {
                appendPathSegments("todos")
            }
        }.body()

    override suspend fun fetchTodoById(id: Int): TodoDto =
        client.get {
            url {
                appendPathSegments("todos", id.toString())
            }
        }.body()

    override suspend fun addTodo(todo: CreateTodoRequest): TodoDto =
        client.post {
            url {
                appendPathSegments("todos")
            }
            contentType(ContentType.Application.Json)
            setBody(todo)
        }.body()


    override suspend fun updateTodo(id: Int, todo: TodoDto): TodoDto =
        client.put {
            url {
                appendPathSegments("todos",id.toString())
            }
            contentType(ContentType.Application.Json)
            setBody(todo)
        }.body()


    override suspend fun setTodoDone(id: Int, patch: TodoCompletedPatch): TodoDto =
        client.patch {
            url {
                appendPathSegments("todos",id.toString())
            }
            contentType(ContentType.Application.Json)
            setBody(patch)
        }.body()


    override suspend fun deleteTodo(id: Int) {
        client.delete {
            url {
                appendPathSegments("todos",id.toString())
            }
        }
    }
}
