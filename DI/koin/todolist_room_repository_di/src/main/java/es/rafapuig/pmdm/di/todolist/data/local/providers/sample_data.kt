package es.rafapuig.pmdm.persistence.room.todolist.data.local.providers

import es.rafapuig.pmdm.di.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.di.todolist.data.local.mappers.toDatabase
import es.rafapuig.pmdm.di.todolist.domain.sampleTodos

val sampleData =
    sampleTodos.map { it.toDatabase() }


suspend fun TodosDatabase.loadSampleData() =
    todoDao().upsertAll(sampleData)