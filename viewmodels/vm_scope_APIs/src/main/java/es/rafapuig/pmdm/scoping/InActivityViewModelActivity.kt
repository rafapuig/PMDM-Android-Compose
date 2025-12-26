package es.rafapuig.pmdm.scoping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.scoping.presentation.NotePadViewModel
import es.rafapuig.pmdm.scoping.presentation.screens.NotePadScreen
import es.rafapuig.pmdm.scoping.ui.theme.PMDMComposeTheme

class InActivityViewModelActivity : ComponentActivity() {

    /**
     * Cuando estamos en una Activity
     * podemos usar la funcion viewModels()
     * para obtener un ViewModel asociado a la Activity
     *
     * Podemos llamarla en la declaración de la propiedad sin que
     * produzca la excepción ya que devuelve un delegado lazy
     * Por eso usamos el by en la declaración de la propiedad
     *
     * Esta función esta definida en el artefacto
     * androidx.activity:activity
     */
    val viewModel by viewModels<NotePadViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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