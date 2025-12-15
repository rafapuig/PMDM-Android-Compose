package es.rafapuig.pmdm.compose.lifecycle.navigation.kotlin_idiomatic.bundle_DSL

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class DestinationActivity : ComponentActivity() {

    companion object {
        fun createIntent(context: Context, name: String): Intent {
            return Intent(context, DestinationActivity::class.java).apply {
                putExtra("name", name)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("name") ?: "Desconocido"

        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
                    ) {
                        Text("Actividad destino")
                        Text("Hola, $name")
                        Button(onClick = { onOk() }) {
                            Text(text = "Aceptar")
                        }
                    }
                }
            }
        }
    }

    fun onOk() {
        finish()
    }
}