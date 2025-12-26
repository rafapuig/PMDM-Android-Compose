@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.scoping.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.scoping.ui.theme.PMDMComposeTheme

@Composable
fun NotePadScreen(
    text: String,
    onTextChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Bloc de notas") }
            )
        }
    ) { paddingValues ->

        TextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            placeholder = { Text("Escribe algo...") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NotePadScreenPreview() {
    PMDMComposeTheme {
        NotePadScreen(
            text = "Hola, este es mi bloc de notas.\nPuedo escribir múltiples líneas.",
            onTextChange = {}
        )
    }
}
