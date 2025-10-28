package es.rafapuig.pmdm.compose.learning.components.dialogs.timepickers

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TemplateDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        // Contenido del diálogo
        text = content
    )

}

@Preview
@Composable
fun TemplateDialogPreview() {
    TemplateDialog({},{}){
        Text("Contenido de prueba para la preview")
    }
}

