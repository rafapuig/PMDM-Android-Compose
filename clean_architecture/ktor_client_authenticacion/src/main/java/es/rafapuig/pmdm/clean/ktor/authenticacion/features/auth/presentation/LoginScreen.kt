package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation

// 1️⃣ Screen con ViewModel y navegación
@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoginSuccess: () -> Unit) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.success) {
        if (state.success) onLoginSuccess()
    }

    LoginScreenContent(
        state = state,
        onSubmit = { username, password -> viewModel.process(LoginIntent.SubmitLogin(username, password)) }
    )
}

// 2️⃣ Compose puro para previsualización
@Composable
fun LoginScreenContent(state: LoginState, onSubmit: (String, String) -> Unit) {
    Column {
        Text("Login")
        TextField(value = "", onValueChange = {}, placeholder = { Text("Username") })
        TextField(value = "", onValueChange = {}, placeholder = { Text("Password") })
        Button(onClick = { onSubmit("demo", "1234") }) { Text("Login") }
        if (state.isLoading) CircularProgressIndicator()
        state.error?.let { Text(it, color = Color.Red) }
    }
}
