package es.rafapuig.pmdm.clean.ktor.authenticacion.features.home.presentation


import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(viewModel: HomeViewModel, onProfileClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Home")
        Button(onClick = { onProfileClick() }) { Text("Go to Profile") }
        Button(onClick = { viewModel.logout() }) { Text("Logout") }
    }
}
