package es.rafapuig.pmdm.scoping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import es.rafapuig.pmdm.scoping.presentation.NotePadViewModel
import es.rafapuig.pmdm.scoping.presentation.screens.NotePadScreen
import es.rafapuig.pmdm.scoping.ui.theme.PMDMComposeTheme

/**
 * La clase Activity implementa la interface ViewModelStoreOwner
 *
 * En este caso vamos a obtener el ViewModel asociado con la Activity
 * (Se creará una instancia si no existe niguna asociada todavía
 * y si existe se devolverá la referencia a la misma)
 *
 * Cuando se destruya la actividad, debido a un cambio de configuración,
 * y posteriormente sea recreada, el ViewModelProvider devolverá
 * la referencia al ViewModel ya existente y asociado con esta actividad
 */
class MainActivity : ComponentActivity() {

    /**
     * Tratar de obtener un ViewModel antes de que la Activity este en
     * el estado CREATED de su ciclo de vida no es correcto.
     * Producirá esta excepción:
     *
     * java.lang.IllegalStateException:
     * Your activity is not yet attached to the Application instance.
     * You can't request ViewModel before onCreate call.
     */

    /*val viewModelThrowException: NotePadViewModel =
        ViewModelProvider(owner = this)
            .get(NotePadViewModel::class.java)
    }*/

     /** Para que se llame al código que inicializa la propiedad viewModel
     * se debe retrasar el acceso a la propiedad hasta el momento
     * en que se usa por primera vez con la función lazy
     *
     * O declararla como lateinit e inicializarla en onCreate
     */

    /**
     * Se obtiene el ViewModel asociado a la Activity
     * mediante el método get() de ViewModelProvider
     */

    val viewModel1: NotePadViewModel by lazy {
        ViewModelProvider(owner = this)
            .get(NotePadViewModel::class.java)
    }

    /**
     * Forma alternativa de obtener el ViewModel asociado a la Activity
     * más kotlin idiomatica mediante el operador de indexación
     */
    val viewModel: NotePadViewModel by lazy {
        println("Obteniendo la instancia del ViewModel... (by lazy)")
        ViewModelProvider(this)[NotePadViewModel::class.java]
    }

    lateinit var viewModel2: NotePadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("Creando la instancia de la Activity...")

        println("Obteniendo la instancia del ViewModel... (lateinit)")
        viewModel2 = ViewModelProvider(this)[NotePadViewModel::class.java]

        println("Son el mismo ViewModel? ${viewModel === viewModel2}")

        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                NotePadScreen(
                    text = viewModel.text,
                    onTextChange = viewModel::onTextChange
                )
            }
        }
    }
}