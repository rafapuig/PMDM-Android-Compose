package es.rafapuig.pmdm.persistence.room.todolist.data.local.providers

import android.content.Context
import es.rafapuig.pmdm.di.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.di.todolist.data.local.factories.TodosDatabaseFactory

fun Context.getDatabaseInstance(factory: TodosDatabaseFactory) = with(TodosDatabaseProvider) {
    initialize(factory)
    getDatabaseInstance(applicationContext)
}

fun Context.getDatabaseInstance() = with(TodosDatabaseProvider) {
    if (!isInitialized) initialize(InMemoryTodosDatabaseFactory())
    getDatabaseInstance(applicationContext)
}





