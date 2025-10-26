package es.rafapuig.pmdm.compose.learning.navigation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import es.rafapuig.pmdm.compose.learning.navigation.WelcomeScreen

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    CustomTextField(
        title = "Enter your name",
        textState = text,
        onTextChange = { text = it }
    )
}


@Preview(showBackground = true)
@Composable
fun Home(onNavigation: (NavKey) -> Unit = {}) {
    var username by remember { mutableStateOf("") }

    val onTextChange = { text: String ->
        username = text
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CustomTextField(
                title = "Enter your username",
                textState = username,
                onTextChange = onTextChange
            )
            Spacer(Modifier.size(30.dp))
            Button(onClick = { onNavigation(WelcomeScreen(username)) }) {
                Text("Registrarte")
            }
        }
    }

}
