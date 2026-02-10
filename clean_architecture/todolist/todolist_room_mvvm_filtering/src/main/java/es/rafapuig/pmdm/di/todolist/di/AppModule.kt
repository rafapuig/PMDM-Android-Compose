package es.rafapuig.pmdm.di.todolist.di

import es.rafapuig.pmdm.di.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.di.todolist.data.local.TodosRepositoryLocalImpl
import es.rafapuig.pmdm.di.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.di.todolist.data.local.factories.TodosDatabaseFactory
import es.rafapuig.pmdm.di.todolist.domain.repositories.TodosRepository
import es.rafapuig.pmdm.di.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.TodosDatabaseProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule  = module {

    single<TodosDatabaseFactory> { InMemoryTodosDatabaseFactory() }

    single {
        with(TodosDatabaseProvider) {
            if (!isInitialized) initialize(get())
            getDatabaseInstance(androidContext())
        }
    }

    single { get<TodosDatabase>().todoDao() }

    single<TodosRepository> { TodosRepositoryLocalImpl(get()) }

    viewModel { TodoListViewModel(get()) }

}