package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, onRegisterSuccess: () -> Unit) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state.success) {
        if (state.success) onRegisterSuccess()
    }
    RegisterScreenContent(state = state, onSubmit = { u, p, e -> viewModel.process(RegisterIntent.SubmitRegister(u, p, e)) })
}

@Composable
fun RegisterScreenContent(state: RegisterState, onSubmit: (String, String, String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Register")
        Spacer(Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, placeholder = { Text("Username") })
        Spacer(Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, placeholder = { Text("Password") })
        Spacer(Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, placeholder = { Text("Email") })
        Spacer(Modifier.height(8.dp))
        Button(onClick = { onSubmit("demo", "1234", "demo@example.com") }) { Text("Register") }
        if (state.isLoading) CircularProgressIndicator()
        state.error?.let { Text(it, color = Color.Red) }
    }
}
