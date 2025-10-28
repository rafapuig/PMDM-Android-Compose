package es.rafapuig.pmdm.compose.learning.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import es.rafapuig.pmdm.compose.learning.ui.theme.pastel.PastelComposeTheme

data class Person(val name: String, val age: Int)

@Preview(showBackground = true)
@Composable
fun CustomDialog(
    person: Person = Person("Armando Bronca Segura", 23),
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true, // Si se pulsa el botón de atrás desaparece
            dismissOnClickOutside = false, // Si pulso fuera del dialogo desaparece
            securePolicy = SecureFlagPolicy.SecureOn, // No permite captura de pantalla
            usePlatformDefaultWidth = true, // Permite usar el ancho por defecto o personalizado
            decorFitsSystemWindows = false, // Permite usar el espacio del sistema
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = person.name, fontSize = 20.sp)
                    Spacer(Modifier.height(16.dp))

                    Text(buildAnnotatedString {
                        append("tiene ")
                        withStyle(
                            SpanStyle(
                                fontSize = 28.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(person.age.toString())
                        }
                        append(" años")
                    })

                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                    Button(onClick = onConfirm) {
                        Text("Cumplir años")
                    }
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun CustomDialogPreview() {
    var person by remember { mutableStateOf(Person("Armando Bronca Segura", 23)) }
    var showDialog by remember { mutableStateOf(false) }
    PastelComposeTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Custom Dialog App Demo") },
                    navigationIcon = { Icon(Icons.Filled.Cake, null) }
                )
            },
            floatingActionButton = {
                LargeFloatingActionButton(
                    onClick = { showDialog = true },
                    modifier = Modifier.fillMaxWidth(.5f).aspectRatio(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Cake,
                        contentDescription = "Chat",
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxSize(.9f)
                            //.aspectRatio(1f)
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {
            Box(Modifier.padding(it))
        }
        if (showDialog) CustomDialog(
            person,
            onDismiss = { showDialog = false },
            onConfirm = {
                person = person.copy(age = person.age + 1)
                showDialog = false
            }
        )
    }
}