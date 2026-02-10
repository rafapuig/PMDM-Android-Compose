package es.rafapuig.pmdm.persistence.room.todolist.data.local.providers

import android.content.Context
import es.rafapuig.pmdm.di.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.di.todolist.data.local.factories.TodosDatabaseFactory
import org.jetbrains.annotations.VisibleForTesting

object TodosDatabaseProvider {
    @Volatile
    private var instance: TodosDatabase? = null

    /*private lateinit var factory1: RoomDatabaseFactory

    @Synchronized
    fun initialize1(factory: RoomDatabaseFactory) {
        if (::factory1.isInitialized) {
            error("Already initialized")
        }
        this.factory1 = factory
    }*/

    // Factory que sabe cómo crear la DB (disco o memoria)
    private var factory: TodosDatabaseFactory? = null

    /**
     * Inicializa el provider con la factory.
     * Si ya hay una DB creada, la cierra antes de crear la nueva.
     */
    fun initialize(factory: TodosDatabaseFactory) {
        synchronized(this) {
            // Si hay una DB creada, cerrarla
            instance?.close()
            instance = null

            // Setear la nueva factory
            this.factory = factory
        }
    }

    val isInitialized: Boolean get() = factory != null


    fun getDatabaseInstance(context: Context): TodosDatabase {

        val currentFactory = factory ?: error("TodosDatabaseProvider not initialized")

        return instance ?: synchronized(this) {
            instance ?: currentFactory.create(context.applicationContext)
                .also { instance = it }
        }
    }

    /**
     * Solo para tests: limpia la instancia.
     */
    @VisibleForTesting
    fun clear() {
        synchronized(this) {
            instance?.close()
            instance = null
        }
    }
}