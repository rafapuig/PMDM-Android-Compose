package es.rafapuig.pmdm.compose.lifecycle.navigation.example2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class DestinationActivity : ComponentActivity() {

    val TAG = "example1.DestinationActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Podemos interceptar la acción de navegar atrás iniciada por el usuario
         * y realizar alguna acción personalizada (por ejemplo antes de efectivamente volver a atrás)
         * o incluso evitarlo
         * Por defecto, el callback está habilitado
         */
        val callback = onBackPressedDispatcher.addCallback(this) {
            Log.i(TAG, "onBackPressed")
        }

        var isCallbackEnabled by mutableStateOf(callback.isEnabled)

        fun onToggleBackPressedCallback() {
            callback.isEnabled =
                !callback.isEnabled // <- actualiza el valor de la propiedad en el callback
            isCallbackEnabled = callback.isEnabled // <- recompone
        }

        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Actividad destino")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                16.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            Button(onClick = { onOk() }) {
                                Text(text = "Aceptar")
                            }
                            Button(onClick = { onBack() }) {
                                Text(text = "Volver")
                            }
                        }
                        Button({ onToggleBackPressedCallback() }) {
                            Text(
                                text = "${if (!isCallbackEnabled) "Deshabilitar" else "Habilitar"} atrás"
                            )
                        }

                    }
                }
            }
        }
        Log.i(TAG, "onCreate")
    }

    fun onOk() {
        finish()
    }

    /**
     * Solamente si el dispatcher tiene todos los callbacks deshabilitados,
     * se ejecutará el fallbackOnBackPressed
     */
    fun onBack() {
        onBackPressedDispatcher.onBackPressed()
    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }
}