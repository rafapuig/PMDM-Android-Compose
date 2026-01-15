package es.rafapuig.pmdm.di.users.koin.compose.presentation.greeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingUserScreen(
    uiState: GreetingUserUiState,
    onAction: (GreetingUserAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ejemplo Users (Koin)") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = uiState.name,
                onValueChange = { onAction(GreetingUserAction.OnNameChange(it)) },
                label = { Text("Introduce un nombre") },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onAction(GreetingUserAction.OnGreet) }
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    onAction(GreetingUserAction.OnGreet)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Saludar")
            }

            if (uiState.greetingMessage.isNotEmpty()) {
                Text(
                    text = uiState.greetingMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingUserScreenPreview() {
    GreetingUserScreen(
        uiState = GreetingUserUiState(),
        onAction = {}
    )
}