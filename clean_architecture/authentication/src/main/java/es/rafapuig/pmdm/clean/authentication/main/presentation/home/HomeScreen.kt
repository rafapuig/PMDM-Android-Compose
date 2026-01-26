package es.rafapuig.pmdm.clean.authentication.main.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    Scaffold { innerPadding ->

        Column(
            Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenido")

            Spacer(Modifier.height(16.dp))

            Button(onClick = onLogout) {
                Text("Logout")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onLogout = {})
}

