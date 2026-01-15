package es.rafapuig.pmdm.di.todolist

import android.app.Application
import es.rafapuig.pmdm.di.todolist.di.Dependencies

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
        /**
         * Durante la creación del objeto Application
         * inicializamos el objeto que gestiona las dependencias
         * con el contexto de la aplicación
         * Ya que algunas dependencias dependen del contexto de la aplicación
         * y es en onCreate cuando ya está disponible el contexto de la aplicación
         */
        Dependencies.initialize(this.applicationContext)
    }

}