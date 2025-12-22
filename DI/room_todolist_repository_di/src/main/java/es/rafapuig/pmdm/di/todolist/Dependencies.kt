package es.rafapuig.pmdm.di.todolist

import android.content.Context
import es.rafapuig.pmdm.di.todolist.data.local.TodoDao
import es.rafapuig.pmdm.di.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.di.todolist.data.local.TodosRepositoryLocalImpl
import es.rafapuig.pmdm.di.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.di.todolist.data.local.factories.TodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.TodosDatabaseProvider
import es.rafapuig.pmdm.di.todolist.domain.repositories.TodosRepository

object Dependencies {

    lateinit var applicationContext: Context

    fun initialize(context: Context) {
        applicationContext = context.applicationContext
    }

    private val providers = mutableMapOf<Class<*>, () -> Any>()

    init {

        providers[Context::class.java] = { applicationContext }

        providers[TodosDatabaseFactory::class.java] = { InMemoryTodosDatabaseFactory() }

        providers[TodosDatabase::class.java] = {
            with(TodosDatabaseProvider) {
                if (!isInitialized) initialize(
                    get(TodosDatabaseFactory::class.java)
                )
                getDatabaseInstance(get(Context::class.java))
            }
        }

        providers[TodoDao::class.java] = {
            val db = get(TodosDatabase::class.java)
            db.todoDao()
        }

        providers[TodosRepository::class.java] = {
            TodosRepositoryLocalImpl(
                get(TodoDao::class.java)
            )
        }
    }

    fun <T> get(clazz: Class<T>): T =
        providers[clazz]?.invoke() as? T
            ?: error("Dependency not found: $clazz")

    inline fun <reified T> get(): T = get(T::class.java)

}
