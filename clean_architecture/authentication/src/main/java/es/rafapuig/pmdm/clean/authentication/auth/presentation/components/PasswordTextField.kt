package es.rafapuig.pmdm.clean.authentication.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.clean.authentication.R

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    initialPasswordVisible: Boolean = false
) {
    var passwordVisible by remember {
        mutableStateOf(initialPasswordVisible)
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(stringResource(R.string.password)) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            capitalization = KeyboardCapitalization.None
        ),
        trailingIcon = {
            IconButton(onClick = ::togglePasswordVisibility) {
                Icon(
                    imageVector =
                        if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff,
                    contentDescription = stringResource(R.string.show_password)
                )
            }
        },
        visualTransformation =
            if (!passwordVisible)
                PasswordVisualTransformation()
            else VisualTransformation.None
    )
}

@Preview(
    showBackground = true, locale = "es"
)
@Composable
fun PasswordTextFieldPreview() {

    var password by remember { mutableStateOf("") }

    PasswordTextField(
        password = password,
        onPasswordChange = { password = it }
    )
}