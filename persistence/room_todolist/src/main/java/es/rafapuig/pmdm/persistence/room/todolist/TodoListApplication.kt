package es.rafapuig.pmdm.persistence.room.todolist

import android.app.Application
import es.rafapuig.pmdm.persistence.room.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.TodosDatabaseProvider
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.getDatabaseInstance

class TodoListApplication() : Application() {

    lateinit var todosDatabase: TodosDatabase
    private set

    override fun onCreate() {
        super.onCreate()
        val factory = InMemoryTodosDatabaseFactory()
        TodosDatabaseProvider.initialize(factory)
        todosDatabase = getDatabaseInstance()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when {
            level >= TRIM_MEMORY_MODERATE -> {
                // Application is running low on memory
            }

            level >= TRIM_MEMORY_BACKGROUND -> {
                // Application goes to background
            }

            level >= TRIM_MEMORY_UI_HIDDEN -> {
                // UI goes away
            }

            level >= TRIM_MEMORY_RUNNING_CRITICAL -> {
                // Application is running low on memory
            }

            level >= TRIM_MEMORY_RUNNING_LOW -> {
                // Application is running low on memory
            }

            else -> {
                // Application is running low on memory
            }
        }
    }

}