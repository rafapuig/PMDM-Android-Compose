package es.rafapuig.pmdm.compose.learning.components.text.textfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.launch

data class Credentials(val username: String, val password: String)

@Preview(showBackground = true)
@Composable
fun Login(
    username: String = "",
    password: String = "",
    onLogin: ((Credentials) -> Unit)? = null
) {
    var username by remember { mutableStateOf(username) }

    var password by remember { mutableStateOf(password) }

    var isPasswordVisible by remember {
        mutableStateOf(true)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Inicio de sesión",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            placeholder = { Text("Introduce tu nombre de usuario") },
            supportingText = { Text("Introduce tu nombre de usuario") },
            shape = CircleShape,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(.9f)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            placeholder = { Text("Introduce tu contraseña") },
            supportingText = { Text("Introduce tu contraseña") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = CircleShape,
            singleLine = true,
            trailingIcon = {
                val trailingIcon =
                    if (isPasswordVisible) Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                val description =
                    if (isPasswordVisible) "Ocultar contraseña"
                    else "Mostrar contraseña"
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        trailingIcon,
                        contentDescription = description
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(.9f)
        )

        Button(
            modifier = Modifier.fillMaxWidth(.6f),
            onClick = {
                onLogin?.invoke(Credentials(username, password))
            }) {
            Text("Login", fontSize = 18.sp)
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginPreview() {
    // 1️⃣ Estado del SnackbarHost
    val snackbarHostState = remember { SnackbarHostState() }

    // 2️⃣ Scope para lanzar corutinas (mostrar snackbar es suspend)
    val coroutineScope = rememberCoroutineScope()

    PMDMComposeTheme {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Login { credentials ->

                    coroutineScope.launch {
                        val message =
                            if (credentials.check()) "Bienvenido: ${credentials.username}"
                            else "Error de autenticación"

                        // 3️⃣ Mostrar el Snackbar
                        snackbarHostState.showSnackbar(
                            message = message,
                            //actionLabel = "Deshacer"
                        )
                    }
                }
            }
        }
    }
}

fun Credentials.check() = username == "Perico" && password == "1234"

