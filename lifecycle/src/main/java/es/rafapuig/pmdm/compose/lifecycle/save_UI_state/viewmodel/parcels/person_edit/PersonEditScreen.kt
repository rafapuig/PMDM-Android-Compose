package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_edit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

@Composable
fun PersonEditScreenRoot(viewModel: PersonEditViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    PersonEditScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}


@Composable
fun PersonEditScreen(
    uiState: PersonEditUiState = PersonEditUiState.Empty,
    onAction: (PersonEditAction) -> Unit = {}
) {
    Scaffold { innerPadding ->
        PersonEditContent(
            uiState = uiState,
            onAction = onAction,
            modifier = Modifier.padding(innerPadding)
        )
    }

}

@Composable
fun PersonEditContent(
    uiState: PersonEditUiState = PersonEditUiState.Empty,
    onAction: (PersonEditAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val person = uiState.person
    val isLoading = uiState.isLoading

    Column(modifier = modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.weight(0.5f))

        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {

            Text(
                text = "Editar Person details",
                style = MaterialTheme.typography.headlineMedium
            )

            TextField(
                value = person?.name ?: "",
                onValueChange = {
                    onAction(PersonEditAction.OnNameChanged(it))
                },
                label = { Text(text = "Nombre") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            TextField(
                value = person?.age?.toString() ?: "",
                onValueChange = {
                    onAction(
                        PersonEditAction.OnAgeChanged(it.toIntOrNull())
                    )
                },
                label = { Text(text = "Edad") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)

            ) {
                Button(
                    enabled = !isLoading,
                    onClick = { onAction(PersonEditAction.OnLoadPerson) }) {
                    Text(text = "Cargar")
                }
                Button(onClick = { onAction(PersonEditAction.OnClearPerson) }) {
                    Text(text = "Limpiar")
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(0.9f)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(.2f)
                        .aspectRatio(1f),
                    strokeWidth = 6.dp
                )
            }
        }
    }


}

@Composable
fun LoadingScreen() {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(64.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .aspectRatio(1f)
                    .padding(16.dp),
                strokeWidth = 16.dp
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PersonEditScreenPreview() {
    PMDMComposeTheme {
        PersonEditScreen(
            uiState = PersonEditUiState(
                person = Person(
                    name = "Rafa Puig",
                    age = 48
                ),
                isLoading = true
            )
        )
    }
}