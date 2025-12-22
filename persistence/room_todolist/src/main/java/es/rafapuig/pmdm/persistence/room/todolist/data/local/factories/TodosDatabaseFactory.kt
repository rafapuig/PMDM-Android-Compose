package es.rafapuig.pmdm.persistence.room.todolist.data.local.factories

import android.content.Context
import es.rafapuig.pmdm.persistence.room.todolist.data.local.TodosDatabase

interface TodosDatabaseFactory {
    fun create(context: Context): TodosDatabase
}