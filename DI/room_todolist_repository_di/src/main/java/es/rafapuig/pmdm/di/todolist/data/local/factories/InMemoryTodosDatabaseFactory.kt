package es.rafapuig.pmdm.di.todolist.data.local.factories

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import es.rafapuig.pmdm.di.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.loadSampleData

open class InMemoryTodosDatabaseFactory(
    shouldLoadData: Boolean = true,
    loadData: suspend TodosDatabase.() -> Unit = {
        this.loadSampleData()
    }
) : AbstractTodosDatabaseFactory(shouldLoadData, loadData) {

    override fun createBuilder(context: Context): RoomDatabase.Builder<TodosDatabase> =
        Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            TodosDatabase::class.java
        )
}