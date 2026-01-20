package es.rafapuig.pmdm.client.todolist.data.remote

import es.rafapuig.pmdm.client.todolist.data.remote.dto.CreateTodoRequest
import es.rafapuig.pmdm.client.todolist.data.remote.dto.TodoCompletedPatch
import es.rafapuig.pmdm.client.todolist.data.remote.dto.TodoDto

interface TodoApiService {


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