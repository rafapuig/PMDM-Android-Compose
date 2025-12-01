package es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DiceScreen(
    uiState: DiceUiState,
    onAction: (DiceAction) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val isLoading = uiState.isRolling
    val dice = uiState.dice

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Dado", style = MaterialTheme.typography.headlineMedium)
            if (isLoading) {
                Text("Tirando...")
            } else {
                Text( "${dice.value}", style = MaterialTheme.typography.displayLarge)
            }
            Button(onClick = { onAction(DiceAction.Roll) }) {
                Text("Tirar")
            }
        }
    }
}