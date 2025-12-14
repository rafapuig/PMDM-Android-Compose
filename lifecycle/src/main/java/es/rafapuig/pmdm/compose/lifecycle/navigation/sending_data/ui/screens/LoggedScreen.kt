package es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.models.Credentials

@Composable
fun LoggedScreen(
    credentials: Credentials,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {

        if (credentials.username == "perico" && credentials.password == "palotes")
            Text("Logged in")
        else
            Text("Invalid credentials")

        Button(onClick = { onBack() }) {
            Text(text = "Volver")
        }
    }


}