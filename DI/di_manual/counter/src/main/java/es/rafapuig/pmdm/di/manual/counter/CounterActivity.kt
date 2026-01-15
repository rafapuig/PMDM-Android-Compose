package es.rafapuig.pmdm.di.manual.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.di.manual.counter.data.CounterDataStore
import es.rafapuig.pmdm.di.manual.counter.data.CounterRepositoryImpl
import es.rafapuig.pmdm.di.manual.counter.data.counterDataStore
import es.rafapuig.pmdm.di.manual.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.di.manual.counter.presentation.CounterViewModelFactory
import es.rafapuig.pmdm.di.manual.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.di.manual.counter.ui.theme.PMDMComposeTheme

class CounterActivity : ComponentActivity() {

    /**
     * Obtenemos el viewmodel mediante la generación manual
     * de las dependencias mecesarias para instanciarlo */
    val viewModel : CounterViewModel by viewModels {

        /**
         * la propiedad application devuelve la referencia a la aplicacion
         * a la que pertenece la actividad
         * (La actividad no se adjunta a la aplicacion hasta que la actividad pase
         * al estado CREATED de su ciclo de vida
         * por tanto, hasta ese momento no tendremos referencia a la aplicacion
         * que posee la actividad y llamar a la propiedad lanzará una excepción
         * Como la funcion viewModels devuevle un delegado Lazy el codigo de esta
         * lambda no se ejecuta hasta que se accede por primera vez la variable viewModel,
         * que como puedes observar se lleva a cabo al usarla como argumento de
         * MVICounterScreenRoot en el setContent que esta dentro del cuerpo de la
         * función de callback onCreate del ciclo de vida de la actividad.
         * Es decir, el callback que es invocado cuando se adjunta la actividad a la aplicación)
         *
         * A partir de application podemos obtener el contexto de la aplicacion
         */
        val context = application.applicationContext

        /**
         * La clase CounterDataStore depende de un DataStore<Preferences>
         */
        val counterDataStore = CounterDataStore(context.counterDataStore)

        /**
         * La clase CounterRepository depende de un CounterDataStore
         */
        val counterRepository = CounterRepositoryImpl(counterDataStore)

        /**
         * La clase CounterViewModelFactory depende de un CounterRepository
         */
        CounterViewModelFactory(counterRepository)
    }

    /**
     * Función onCreate callback de ciclo de vida de la actividad
     * Es llamada por el sistema cuando la actividad pasa al estado CREATED
     * porque ya ha sido adjuntada a la aplicación a la cual pertenecerá.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                MVICounterScreenRoot(viewModel)
            }
        }
    }
}