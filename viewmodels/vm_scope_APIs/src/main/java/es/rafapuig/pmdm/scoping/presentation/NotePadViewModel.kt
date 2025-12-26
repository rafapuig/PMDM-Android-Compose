package es.rafapuig.pmdm.scoping.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Un ViewModel hereda de la clase ViewModel de Android Jetpack
 */
class NotePadViewModel : ViewModel() {



    /**
     * Se ejecuta cuando se crea la instancia del ViewModel
     * que será una unica vez por ViewModelStoreOwner
     * es decir, cuando cambie la comfiguración del dispositivo se reeará
     * la instancia de la Actividad pero no el ViewModel asociado con esta
     * por tanto, no se vuelve a iniciar el ViewModel
     * (Compruebalo rotando el dispositivoy mirando el LogCat)
     */
    init {
        println("Creando la instancia NotePadViewModel...")
    }

    /**
     * Mantiene el estado de la UI
     */

    var text by mutableStateOf("")
    private set

    /**
     * Actualiza el estado de la UI
     */
    fun onTextChange(newText: String) {
        text = newText
    }

    /**
     * El metodo onCleared
     * se llama cuando se destruye el ViewModel
     * La destrucción se lleva a cabo cuando su StoreOwner
     * (En este caso la Activity) se elimina de la pila de actividades
     * del backstack (por ejemplo cuando el usuario pulsa el botón de
     * atrás)
     */
    override fun onCleared() {
        super.onCleared()
        println("Destruyendo la instancia NotePadViewModel...")
    }
}