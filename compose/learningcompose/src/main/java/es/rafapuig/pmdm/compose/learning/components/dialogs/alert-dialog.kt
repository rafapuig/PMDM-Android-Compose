package es.rafapuig.pmdm.compose.learning.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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

/**
 * Un AlertDialog donde solo proporcionamos los argumentos obligatorios mínimos
 */
@Preview(showBackground = true)
@Composable
fun MostSimpleAlertDialog(
    onDismissRequest: () -> Unit = { },
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("Confirmar")
            }
        }
    )
}

@Preview
@Composable
fun ExampleAlertDialog(
    onDismissRequest: () -> Unit = { },
    onConfirm: () -> Unit = { },
) {
    AlertDialog(
        icon = {
            Icon(Icons.Filled.Info, contentDescription = "Alerta")
        },
        title = {
            Text("Título del AlertDialog")
        },
        text = {
            Text("Esto es un ejemplo de un alert dialog con botones")
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancelar")
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun AlertDialogDemoScreen(
    showMostSimpleDialog: Boolean = false,
    showExampleDialog: Boolean = true,
) {

    var openMostSimpleDialog by remember { mutableStateOf(showMostSimpleDialog) }
    var openExampleDialog by remember { mutableStateOf(showExampleDialog) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { openMostSimpleDialog = !openMostSimpleDialog }) {
            Text("Mostrar AlertDialog")
        }
        Button(onClick = { openExampleDialog = !openExampleDialog }) {
            Text("Mostrar AlertDialog")
        }
    }

    when {
        openMostSimpleDialog -> MostSimpleAlertDialog { openMostSimpleDialog = false }
        openExampleDialog -> ExampleAlertDialog(
            onDismissRequest = { openExampleDialog = false },
            onConfirm = { openExampleDialog = false }
        )
    }

}