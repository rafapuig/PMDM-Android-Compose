package es.rafapuig.pmdm.compose.exercises.viewmodel.exercise3d

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TemperatureOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    suffix: String,
    isComputing: Boolean,
    modifier: Modifier = Modifier
) {
    val labelText =
        if (isComputing && value.isEmpty()) "Calculando..."
        else label

    val trailingIcon = @Composable {
        if (value.isNotBlank()) {
            IconButton(onClick = { onValueChange("") }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear"
                )
            }
        }
    }


    val trailingIconNullable: @Composable (() -> Unit)? =
        if (value.isNotBlank()) trailingIcon else null

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        suffix = { Text(" $suffix") },
        leadingIcon = {
            if (isComputing) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(4.dp),
                    strokeWidth = 2.dp
                )
            }
        },
        trailingIcon = trailingIcon
    )
}


@Preview(showBackground = true)
@Composable
fun TemperatureOutlinedTextFieldPreview() {
    TemperatureOutlinedTextField(
        value = "20",
        onValueChange = {},
        label = "Celsius",
        suffix = "°C",
        isComputing = false
    )
}

@Preview(showBackground = true)
@Composable
fun TemperatureOutlinedTextFieldPreview2() {
    TemperatureOutlinedTextField(
        value = "",
        onValueChange = {},
        label = "Celsius",
        suffix = "°C",
        isComputing = true
    )
}