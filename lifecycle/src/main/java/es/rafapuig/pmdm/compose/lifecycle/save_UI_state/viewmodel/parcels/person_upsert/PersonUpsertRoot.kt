package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

@Composable
fun PersonUpsertScreenRoot(viewModel: PersonUpsertViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PersonUpsertScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Preview
@Composable
fun PersonUpsertScreenRootPreview() {
    PMDMComposeTheme {
        PersonUpsertScreenRoot()
    }
}