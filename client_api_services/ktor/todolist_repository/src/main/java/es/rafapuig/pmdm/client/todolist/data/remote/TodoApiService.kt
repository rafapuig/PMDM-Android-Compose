package es.rafapuig.pmdm.client.todolist.data.remote

import es.rafapuig.pmdm.client.todolist.data.remote.dto.CreateTodoRequest
import es.rafapuig.pmdm.client.todolist.data.remote.dto.TodoCompletedPatch
import es.rafapuig.pmdm.client.todolist.data.remote.dto.TodoDto

interface TodoApiService {
    companion object {
        const val API_HOST = "ktor-todos.fly.dev"
    }

    object HttpRoutes {
        const val ALL_TODOS_ENDPOINT = "todos"
        const val TODO_BY_ID_ENDPOINT = "todos/{id}"
        const val ADD_TODO_ENDPOINT = "todos"
        const val UPDATE_TODO_ENDPOINT = "todos/{id}"
        const val SET_TODO_DONE_ENDPOINT = "todos/{id}"
        const val DELETE_TODO_ENDPOINT = "todos/{id}"
    }

    // READ (lista) GET
    suspend fun fetchAllTodos(): List<TodoDto>

    // READ (uno) GET
    suspend fun fetchTodoById(id: Int): TodoDto

    // CREATE (POST)
    suspend fun addTodo(todo: CreateTodoRequest): TodoDto

    // UPDATE completo (PUT)
    suspend fun updateTodo(id: Int, todo: TodoDto): TodoDto

    // UPDATE parcial (PATCH)
    suspend fun setTodoDone(id: Int, patch: TodoCompletedPatch): TodoDto

    // DELETE (DELETE)
    suspend fun deleteTodo(id: Int)
}