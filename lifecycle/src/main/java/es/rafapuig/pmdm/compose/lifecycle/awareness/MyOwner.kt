package es.rafapuig.pmdm.compose.lifecycle.awareness

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * Este es un ejemplo de como implementar un LifecycleOwner
 * El owner poseerá el ciclo de vida de la Activity (propiedad lifecycle)
 *
 * La implementación del interface consiste en implementar
 * el método getLifecycle(), en kotlin la propiedad de solo lectura lifecycle
 */
class MyOwner(): LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    fun startOwner() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun stopOwner() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    fun resumeOwner() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    fun pauseOwner() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }



    override val lifecycle: Lifecycle
        get() = lifecycleRegistry



    init {
        //lifecycle.addObserver(MyObserver())
    }


}