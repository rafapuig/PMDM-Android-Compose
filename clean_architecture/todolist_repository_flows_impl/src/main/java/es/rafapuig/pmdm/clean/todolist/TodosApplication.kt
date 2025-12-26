package es.rafapuig.pmdm.clean.todolist

import android.app.Application
import es.rafapuig.pmdm.clean.todolist.data.repositories.TodosRepositoryImpl

class TodosApplication : Application() {

    val repository by lazy { TodosRepositoryImpl() }

}