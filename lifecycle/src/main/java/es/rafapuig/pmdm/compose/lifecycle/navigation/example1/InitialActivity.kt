package es.rafapuig.pmdm.compose.lifecycle.navigation.example1

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class InitialActivity : ComponentActivity() {

    val TAG = "example1.InitialActivity"

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
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = { onNavigate() }) {
                            Text(text = "Navegar a Actividad Destino")
                        }
                    }
                }
            }
        }
        Log.i(TAG, "onCreate")
    }

    /**
     * Usamos un Intent explicito para navegar a la actividad destino
     * ese intent se pasa al metodo startActivity() de la clase Activity
     */
    fun onNavigate() {
        val intent = Intent(this, DestinationActivity::class.java)
        startActivity(intent)
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