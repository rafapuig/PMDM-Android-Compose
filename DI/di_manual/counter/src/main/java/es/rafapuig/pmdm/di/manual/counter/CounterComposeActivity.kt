package es.rafapuig.pmdm.di.manual.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.di.manual.counter.data.CounterDataStore
import es.rafapuig.pmdm.di.manual.counter.data.CounterRepositoryImpl
import es.rafapuig.pmdm.di.manual.counter.data.counterDataStore
import es.rafapuig.pmdm.di.manual.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.di.manual.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.di.manual.counter.ui.theme.PMDMComposeTheme

class CounterComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                val viewModel = provideCounterViewModel()

                MVICounterScreenRoot(viewModel)
            }
        }
    }
}

/**
 * provideCounterViewModel es una función composable
 * (RECUERDA: Las funciones composables que devuelven un valor.
 * se nombran usando notacion camelCase por eso empieza por minúscula)
 *
 * lo cual nos permite usar la función viewModel del API de ViewModel Scoping
 * ya que esta es una función composable.
 * (RECUERDA: solamente se puede llamar a funciones composables desde otras funciones composables)
 */

@Composable
private fun provideCounterViewModel() : CounterViewModel {
    /** Creamos el viewmodel mediante la obtención manual de las dependencias */

    /** Si llamamos a provideCounterViewModel dentro de algun miembro de una clase
     * Activity, LocalContext.current nos devuelve el contexto de la actividad
     */
    val context = LocalContext.current

    /**
     * Llamanos a la función viewModel del ViewModel Scoping API para funciones composables
     * El último parámetro "initializer" es de tipo
     * objeto función con receptor que devuelve un ViewModel.
     * Por tanto, podemos usar como argumento para initializer una lambda
     * que devuelva un ViewModel
     */
    val viewModel = viewModel {
        /** Creamos el datastore del que depende el repositorio */
        val counterDataStore = CounterDataStore(context.applicationContext.counterDataStore)

        /** Creamos el repositorio del que depende el viewmodel */
        val counterRepository = CounterRepositoryImpl(counterDataStore)

        /** Creamos el viewModel pasándole (inyectándole) el repositorio al contructor */
        CounterViewModel(counterRepository)
    }

    return viewModel
}