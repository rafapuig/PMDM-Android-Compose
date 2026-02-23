package es.rafapuig.pmdm.compose.learning.components.text.textfields.password

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordToggleVisibilityButtonIcon(
    isPasswordVisible: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit = {}
) {
    val trailingIcon =
        if (isPasswordVisible) Icons.Filled.Visibility
        else Icons.Filled.VisibilityOff

    val description =
        if (isPasswordVisible) "Ocultar contraseña"
        else "Mostrar contraseña"

    IconButton(
        onClick = { onPasswordVisibilityChange(!isPasswordVisible) }
    ) {
        Icon(
            trailingIcon,
            contentDescription = description
        )
    }
}

@Preview
@Composable
fun PasswordToggleVisibilityButtonIconPreview() {
    PasswordToggleVisibilityButtonIcon(isPasswordVisible = true)
}

@Preview
@Composable
fun PasswordToggleVisibilityButtonIconPreview2() {
    PasswordToggleVisibilityButtonIcon(isPasswordVisible = false)
}

@Preview
@Composable
fun PasswordToggleVisibilityButtonIconPreview3() {

    var isPasswordVisible by remember { mutableStateOf(false) }

    PasswordToggleVisibilityButtonIcon(
        isPasswordVisible = isPasswordVisible,
        onPasswordVisibilityChange = { isPasswordVisible = it }
    )
}