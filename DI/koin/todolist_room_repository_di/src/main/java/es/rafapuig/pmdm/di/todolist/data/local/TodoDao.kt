package es.rafapuig.pmdm.di.todolist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import es.rafapuig.pmdm.di.todolist.data.local.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getTodoById(id: Int): Flow<TodoEntity>

    @Query("SELECT * FROM todos WHERE is_done = :isDone")
    fun getByDone(isDone: Boolean): Flow<List<TodoEntity>>


    @Upsert
    suspend fun upsert(todo: TodoEntity)

    @Upsert
    suspend fun upsertAll(todos: List<TodoEntity>)

    @Delete
    suspend fun delete(todo: TodoEntity)
}