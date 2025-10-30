package es.rafapuig.pmdm.compose.exercises.navigation.exercise1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToProfile: (Int) -> Unit = {},
    onNavigateToSettings: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onNavigateToProfile(42) }) {
            Text("Ir al perfil")
        }
        Button(onClick = { onNavigateToSettings() }) {
            Text("Ir a ajustes")
        }
    }
}