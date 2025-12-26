@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.ui_state_holder.uiState_class.core.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.ui_state_holder.uiState_class.core.presentation.UiState
import es.rafapuig.pmdm.ui_state_holder.uiState_class.core.presentation.UpperCaseVisualTransformation
import es.rafapuig.pmdm.ui_state_holder.uiState_class.ui.theme.PMDMComposeTheme

@Composable
fun UiStateDemoScreen(
    uiState: UiState,
    onNameChange: (String) -> Unit,
    onUpperCaseChange: (Boolean) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("UiState class Demo") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            )
        ) {

            TextField(
                value = uiState.name,
                onValueChange = onNameChange,
                label = { Text("Nombre") },
                placeholder = { Text("Introduce tu nombre") },
                visualTransformation = if (uiState.upperCase) UpperCaseVisualTransformation() else VisualTransformation.None
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Mayusculas")
                Switch(
                    checked = uiState.upperCase,
                    onCheckedChange = onUpperCaseChange
                )
            }

        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NotePadScreenPreview() {
    PMDMComposeTheme {
        UiStateDemoScreen(
            uiState = UiState(
                name = "Perico Palotes",
                upperCase = false
            ),
            onNameChange = {},
            onUpperCaseChange = {}
        )
    }
}
