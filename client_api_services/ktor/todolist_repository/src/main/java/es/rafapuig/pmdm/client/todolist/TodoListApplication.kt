package es.rafapuig.pmdm.client.todolist

import android.app.Application
import es.rafapuig.pmdm.client.todolist.data.TodoRepositoryRemoteImpl
import es.rafapuig.pmdm.client.todolist.data.remote.NetworkModule
import es.rafapuig.pmdm.client.todolist.domain.repositories.TodoRepository

class TodoListApplication() : Application() {

    lateinit var repository: TodoRepository
    private set


    override fun onCreate() {
        super.onCreate()

       repository = TodoRepositoryRemoteImpl(NetworkModule.apiService)
    }

}