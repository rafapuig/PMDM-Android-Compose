package es.rafapuig.pmdm.di.todolist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import es.rafapuig.pmdm.di.todolist.data.local.entities.TodoEntity
import es.rafapuig.pmdm.di.todolist.data.local.factories.TodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.TodosDatabaseProvider

@Database(
    entities = [TodoEntity::class],
    version = 1
)
abstract class TodosDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object Factory {

        const val DB_NAME = "todos_database.db"

        operator fun invoke(factory: TodosDatabaseFactory) : Factory {
            TodosDatabaseProvider.initialize(factory)
            return this
        }

        fun getInstance(context: Context): TodosDatabase =
            TodosDatabaseProvider.getDatabaseInstance(context)
    }
}
