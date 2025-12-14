package es.rafapuig.pmdm.compose.lifecycle.navigation.factory_functions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class InitialActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
                    ) {
                        var text by remember { mutableStateOf("") }
                        Text("Actividad inicial")
                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text("Nombre") },
                            singleLine = true
                        )
                        Button(onClick = { onNavigate(text) }) {
                            Text(text = "Navegar a Actividad Destino")
                        }
                    }
                }
            }
        }
    }

    /**
     * Usamos un Intent explicito para navegar a la actividad destino
     * ese intent se pasa al metodo startActivity() de la clase Activity
     * El intent nos lo proporciona el factory function de la actividad destino
     */
    fun onNavigate(name: String) {
        val intent = DestinationActivity.createIntent(this, name = name)
        startActivity(intent)
    }

}