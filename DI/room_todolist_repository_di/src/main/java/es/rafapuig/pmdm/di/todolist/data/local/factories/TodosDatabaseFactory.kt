package es.rafapuig.pmdm.di.todolist.data.local.factories

import android.content.Context
import es.rafapuig.pmdm.di.todolist.data.local.TodosDatabase

interface TodosDatabaseFactory {
    fun create(context: Context): TodosDatabase
}