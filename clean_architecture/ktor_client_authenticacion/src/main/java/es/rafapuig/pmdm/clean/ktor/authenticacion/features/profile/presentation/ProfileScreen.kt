package es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.presentation

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) { viewModel.process(ProfileIntent.LoadProfile) }
    ProfileScreenContent(state)
}

@Composable
fun ProfileScreenContent(state: ProfileState) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Profile")
        state.profile?.let {
            Text("Username: ${it.username}")
            Text("Email: ${it.email}")
        }
        state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        if(state.isLoading) CircularProgressIndicator()
    }
}
