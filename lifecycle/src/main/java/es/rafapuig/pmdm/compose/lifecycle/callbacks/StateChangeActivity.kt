package es.rafapuig.pmdm.compose.lifecycle.callbacks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

/** https://developer.android.com/guide/components/activities/activity-lifecycle */

/**
 * Revisar el LogCat con tag:StateChangeActivity
 * Al iniciar la activity
 * onCreate
 * onStart
 * onResume
 *
 * Pulsar el botón Home del dispositivo
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
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.Companion.CenterHorizontally,
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
     * Estos callbacks: onSaveInstanceState y onRestoreInstanceState
     * Se usan para guardar y restaurar el estado dinámico de la actividad
     * (estado que no queremos que se pierda al producirse un cambio de configuración)
     */


    /**
     * Es lo mismo que conseguimos con rememberSaveable en Compose
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