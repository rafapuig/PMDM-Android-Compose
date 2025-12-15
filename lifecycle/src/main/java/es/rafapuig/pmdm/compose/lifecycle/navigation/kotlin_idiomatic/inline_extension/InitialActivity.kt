package es.rafapuig.pmdm.compose.lifecycle.navigation.kotlin_idiomatic.inline_extension

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
                    Column(
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterVertically
                        )
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
     * Usamos la función de extension de Context creada para iniciar la actividad DestinationActivity
     * La llamamos usando como objeto Context receptor la instancia de la actividad InitialActivity
     * y pasamos los argumentos de llamada, en este caso el name
     */
    fun onNavigate(name: String) {
        startActivity<DestinationActivity>("name" to name)
        // equivale a
        //this.startActivity<DestinationActivity>("name" to name) // ya que this es el contexto receptor
    }
}

