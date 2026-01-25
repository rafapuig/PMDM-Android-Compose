package es.rafapuig.pmdm.clean.authentication.auth.presentation.register

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.clean.authentication.auth.presentation.components.PasswordTextField
import es.rafapuig.pmdm.clean.authentication.auth.presentation.components.UserEmailTextField
import es.rafapuig.pmdm.clean.authentication.ui.theme.PMDMComposeTheme

@Composable
fun RegisterScreen(
    state: RegisterUiState,
    onRegisterClick: (String, String) -> Unit,
    onBack: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Crear cuenta",
                style = MaterialTheme.typography.headlineMedium
            )

            //Spacer(Modifier.height(16.dp))

            UserEmailTextField(
                text = email,
                onEmailChange = { email = it },
                label = "Email",
                placeholder = "Introduce un email para asociar a tu cuenta"
            )

            PasswordTextField(
                password = password,
                onPasswordChange = { password = it },
                initialPasswordVisible = false
            )

            //Spacer(Modifier.height(16.dp))

            Button(
                onClick = { onRegisterClick(email, password) },
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }

            state.error?.let {
                Spacer(Modifier.height(8.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }

            //Spacer(Modifier.height(8.dp))

            TextButton(onClick = onBack) {
                Text("Volver")
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun RegisterScreenPreview() {
    PMDMComposeTheme {
        RegisterScreen(
            state = RegisterUiState(),
            onRegisterClick = { _, _ -> },
            onBack = {}
        )
    }
}
