package es.rafapuig.pmdm.clean.authentication.auth.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.clean.authentication.R
import es.rafapuig.pmdm.clean.authentication.auth.presentation.components.PasswordTextField
import es.rafapuig.pmdm.clean.authentication.auth.presentation.components.UserEmailTextField
import es.rafapuig.pmdm.clean.authentication.ui.theme.PMDMComposeTheme

@Composable
fun LoginScreen(
    state: LoginUiState,
    onLoginClick: (String, String) -> Unit,
    onCreateAccountClick: () -> Unit
) {
    var email by remember { mutableStateOf("test@test.com") }
    var password by remember { mutableStateOf("1234") }


    Scaffold { innerPadding ->

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.42f)
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(.4f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Iniciar sesión", style = MaterialTheme.typography.headlineMedium)

            UserEmailTextField(
                text = email,
                onEmailChange = { email = it },
                label = "Email",
                placeholder = "Introduce el email de tu cuenta"
            )

            PasswordTextField(
                password = password,
                onPasswordChange = { password = it },
                initialPasswordVisible = false
            )

            Button(
                onClick = { onLoginClick(email, password) },
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesión")
            }

            state.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }

            TextButton(onClick = onCreateAccountClick) {
                Text(
                    "Crear cuenta",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LoginScreenPreview() {
    PMDMComposeTheme {
        LoginScreen(
            state = LoginUiState(),
            onLoginClick = { _, _ -> },
            onCreateAccountClick = {}
        )
    }
}
