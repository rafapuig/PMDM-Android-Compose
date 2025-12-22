package es.rafapuig.pmdm.di.todolist

import android.app.Application

class TodoListApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        Dependencies.initialize(this.applicationContext)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when  {
            level >= TRIM_MEMORY_MODERATE -> {
                // Application is running low on memory
            }
            level >= TRIM_MEMORY_BACKGROUND -> {
                // Application goes to background
            }
            level >= TRIM_MEMORY_UI_HIDDEN -> {
                // UI goes away
            }
            level >= TRIM_MEMORY_RUNNING_CRITICAL -> {
                // Application is running low on memory
            }
            level >= TRIM_MEMORY_RUNNING_LOW -> {
                // Application is running low on memory
            }
            else -> {
                // Application is running low on memory
            }
        }
    }

}