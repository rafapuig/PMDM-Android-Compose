package es.rafapuig.pmdm.compose.learning.components.textfields.password

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String = "Contraseña",
    placeholder: String = "Introduce tu contraseña",
    supportingText: String = "Introduce tu contraseña",
    isError: Boolean = false
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    val visualTransformation =
        if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation()

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = modifier,
        enabled = enabled,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        supportingText = { Text(supportingText) },
        visualTransformation = visualTransformation,
        shape = CircleShape,
        singleLine = true,
        trailingIcon = {
            PasswordToggleVisibilityButtonIcon(
                isPasswordVisible = isPasswordVisible,
                onPasswordVisibilityChange = { isPasswordVisible = it }
            )
        },
        isError = isError
    )
}

/**
 * Pruébala con el modo interactivo (Start Interactive Mode)
 */
@Preview(showBackground = true)
@Composable
fun PasswordFieldPreview() {
    var password by remember { mutableStateOf("") }
    val onPasswordChange: (String) -> Unit = { password = it }

    PasswordField(
        password = password,
        onPasswordChange = onPasswordChange
    )
}