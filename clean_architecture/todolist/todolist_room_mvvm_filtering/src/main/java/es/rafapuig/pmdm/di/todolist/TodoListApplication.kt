package es.rafapuig.pmdm.di.todolist

import android.app.Application
import es.rafapuig.pmdm.di.todolist.di.Dependencies
import es.rafapuig.pmdm.di.todolist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Creamos una clase llamada TodoListApplication que hereda de Application
 * para inicializar el objeto que gestiona las dependencias
 * proporcionandole la referencia al contexto de la aplicación
 *
 * Esta clase hay que especificarla en el manifest
 * en el elemento <application>
 * con el atributo android:name=".TodoListApplication"
 */

class TodoListApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TodoListApplication)
            modules(appModule)
        }
    }
}