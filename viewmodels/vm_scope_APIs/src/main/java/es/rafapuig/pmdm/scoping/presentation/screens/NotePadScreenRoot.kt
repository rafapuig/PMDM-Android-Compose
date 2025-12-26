package es.rafapuig.pmdm.scoping.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.scoping.presentation.NotePadViewModel


/**
 * La función viewModel
 * usa como valor por defecto del parámetro viewModelStoreOwner
 * el ViewModelStoreOwner que se obtiene a través del LocalViewModelStoreOwner
 * (La referencia a la Activity que llama a la función NotePadScreenRoot)
 */

@Composable
fun NotePadScreenRoot(
    viewModel: NotePadViewModel = viewModel()
) {
    NotePadScreen(
        text = viewModel.text,
        onTextChange = viewModel::onTextChange
    )
}