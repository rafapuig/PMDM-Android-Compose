package es.rafapuig.pmdm.scoping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import es.rafapuig.pmdm.scoping.presentation.NotePadViewModel
import es.rafapuig.pmdm.scoping.presentation.screens.NotePadScreen
import es.rafapuig.pmdm.scoping.ui.theme.PMDMComposeTheme

class CoreViewModelAPIActivity : ComponentActivity() {

    /**
     * Retrasamos el acceso a la propiedad viewModel
     * hasta que se use por primera vez
     * El primer acceso a la propiedad viewModel
     * debe suceder después de que la Activity sea creada (onCreate)
     * para que no produzca una excepción
     */

    /**
     * Creamos una instancia ViewModelProvider pasando como
     * ViewModelStoreOwner la Activity (owner = this)
     * Este ViewModelProvider nos proporcionará un ViewModel de tipo NotePadViewModel
     * asociado a la Activity (ya que el ViewModelStore owner es this, que es la Activity)
     */

    val viewModel by lazy {
        ViewModelProvider(this)[NotePadViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                NotePadScreen(
                    text = viewModel.text, // Primer acceso al ViewModel
                    onTextChange = viewModel::onTextChange
                )
            }
        }
    }
}