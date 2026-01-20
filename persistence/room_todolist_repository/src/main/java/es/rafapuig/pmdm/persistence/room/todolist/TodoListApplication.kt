package es.rafapuig.pmdm.persistence.room.todolist

import android.app.Application
import es.rafapuig.pmdm.persistence.room.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.TodosRepositoryLocalImpl
import es.rafapuig.pmdm.persistence.room.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.TodosDatabaseProvider
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.getDatabaseInstance
import es.rafapuig.pmdm.persistence.room.todolist.domain.repositories.TodosRepository

class TodoListApplication() : Application() {

    lateinit var repository: TodosRepository
        private set

    override fun onCreate() {
        super.onCreate()
        val factory = InMemoryTodosDatabaseFactory()
        val todosDatabase: TodosDatabase = getDatabaseInstance(factory)
        repository = TodosRepositoryLocalImpl(todosDatabase.todoDao())
    }

}