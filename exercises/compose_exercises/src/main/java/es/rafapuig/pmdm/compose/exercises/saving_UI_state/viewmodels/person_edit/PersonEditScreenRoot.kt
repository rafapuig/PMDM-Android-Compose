package es.rafapuig.pmdm.compose.exercises.saving_UI_state.viewmodels.person_edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.exercises.ui.theme.ComposeExercisesTheme

@Composable
fun PersonEditScreenRoot(viewModel: PersonEditViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PersonEditScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Preview
@Composable
fun PersonEditScreenRootPreview() {
    ComposeExercisesTheme {
        PersonEditScreenRoot()
    }
}