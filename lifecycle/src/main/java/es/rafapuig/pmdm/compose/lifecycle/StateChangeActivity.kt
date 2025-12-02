package es.rafapuig.pmdm.compose.lifecycle

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

/** https://developer.android.com/guide/components/activities/activity-lifecycle */

/**
 * Revisar el LogCat con tag:StateChangeActivity
 * Al iniciar la activity
 * onCreate
 * onStart
 * onResume
 *
 * Pulsar el boton Home del dispositivo
 * onPause
 * onStop
 * onSaveInstanceState (guardar el estado dinámico)
 *
 * Si volvemos a ver la actividad
 * onRestart
 * onStart
 * onResume
 *
 * Si hacemos un cambio de configuración
 * onPause
 * onStop
 * onSaveInstanceState
 * onDestroy <----- Un cambio de configuración destruye
 * onCreate <------ Y vuelve a crear una nueva instancia Activity
 * onStart
 * onRestoreInstanceState (recuperar el estado dinámico)
 * onResume
 *
 */
class StateChangeActivity : ComponentActivity() {

    private val TAG = "StateChangeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Ciclo de vida de la Activity")
                    }
                }
            }
        }
        Log.i(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }


    /**
     * Es lo mismo que conseguimos con rememberSaveable
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState")
    }

}
