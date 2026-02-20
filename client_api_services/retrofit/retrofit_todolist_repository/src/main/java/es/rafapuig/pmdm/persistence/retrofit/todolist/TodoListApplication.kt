package es.rafapuig.pmdm.persistence.retrofit.todolist

import android.app.Application
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.NetworkModule
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.TodosRepositoryRemoteImpl
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.repositories.TodosRepository

class TodoListApplication() : Application() {

    lateinit var repository: TodosRepository

    override fun onCreate() {
        super.onCreate()

        repository = TodosRepositoryRemoteImpl(NetworkModule.apiService)
    }

}