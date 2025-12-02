package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert.uiState.PersonUpsertUiState
import es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert.uiState.PersonUpsertUiStateBoilerPlateImpl
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

@Composable
fun PersonUpsertScreenRoot(viewModel: PersonUpsertViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    PersonUpsertScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
fun PersonUpsertScreen(
    uiState: PersonUpsertUiState,
    onAction: (PersonUpsertAction) -> Unit = {}
) {
    Scaffold { innerPadding ->
        PersonUpsertContent(
            uiState = uiState,
            onAction = onAction,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun PersonUpsertContent(
    uiState: PersonUpsertUiState,
    onAction: (PersonUpsertAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(text = "Editar Persona", style = MaterialTheme.typography.headlineMedium)
        TextField(
            value = uiState.name,
            onValueChange = {
                onAction(PersonUpsertAction.OnNameChanged(it))
            },
            label = { Text(text = "Nombre") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            )

        )
        TextField(
            value = uiState.age?.toString() ?: "",
            onValueChange = {
                onAction(PersonUpsertAction.OnAgeChanged(it.toIntOrNull()))
            },
            label = { Text(text = "Edad") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = { onAction(PersonUpsertAction.OnClearPerson) }) {
            Text(text = "Limpiar")
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PersonUpsertScreenPreview() {
    PMDMComposeTheme {
        PersonUpsertScreen(
            uiState = PersonUpsertUiStateBoilerPlateImpl()
        )
    }
}