package es.rafapuig.pmdm.di.todolist.data.local

import es.rafapuig.pmdm.di.todolist.data.local.entities.TodoEntity
import es.rafapuig.pmdm.di.todolist.data.local.mappers.toDatabase
import es.rafapuig.pmdm.di.todolist.data.local.mappers.toDomain
import es.rafapuig.pmdm.di.todolist.domain.model.Todo
import es.rafapuig.pmdm.di.todolist.domain.repositories.TodosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class TodosRepositoryLocalImpl(private val dao: TodoDao) : TodosRepository {

    override val todos: Flow<List<Todo>>
        get() = dao.getAllTodos().map { entities ->
            entities.map { entity -> entity.toDomain() }
        }


    override suspend fun setTodoIsDone(todo: Todo, isDone: Boolean) {
        dao.upsert(todo.toDatabase().copy(isDone = isDone))
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.delete(todo.toDatabase())
    }

    override suspend fun addTodo(task: String) {
        dao.upsert(TodoEntity(task = task))
    }
}