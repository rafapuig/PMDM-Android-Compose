package es.rafapuig.pmdm.compose.lifecycle.save_UI_state

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

/** https://developer.android.com/guide/components/activities/activity-lifecycle */

/**
 * El estado de la  UI en actividad se puede persistir
 * con onSaveInstanceState y onRestoreInstanceState
 * (callbacks del ciclo de vida de la Activity)
 * a los cambios de configuración y al system-initiated process death
 *
 * El system-initiated process death destruye la instancia cuando el SO considera
 * que hay que liberar la memoria.
 * Entre las apps candidatas a ser destruidas estan las en primer lugar las que están en segundo plano
 *
 * Para matar (system-initiated process death) la actividad,
 * ponerla en segundo plano pulsando Home del dispositivo
 *
 * se ejecuta el callback onStop y la actividad queda en segundo plano
 *
 * Y luego ejecutar el siguiente comando:
 *
 *  adb shell am kill es.rafapuig.pmdm.compose.lifecycle
 *
 *  Se destruye la actividad pero no se ejecuta el callback onDestroy
 *
 */
class UIStateActivity : ComponentActivity() {

    companion object {
        private const val COUNTER_KEY = "counter"

        private const val TAG = "UIStateActivity"
    }

    var counterState by mutableIntStateOf(0)

    val onClick: () -> Unit = {
        counterState++
        Log.i(TAG, "Incrementar $counterState")
    }

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
                        Text(text = "$counterState")
                        Button(onClick = onClick) {
                            Text(text = "Incrementar")
                        }
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
     *
     * Se ejecuta despues del onStop
     * Porque la actividad ha quedado en segundo plano
     * y está en peligro de ser destruida por el SO si se necesita memoria
     * para otros procesos
     * Por tanto, es el momento de guardar todas la información
     * que queramos que persista en caso de que ocurra system-initiated process death
     * que destruirá la instancia de actividad
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        Log.i(TAG, "Guardar $counterState")
        outState.putInt(COUNTER_KEY, counterState)
    }

    /**
     * Lo que hayamos guardado en onSaveInstanceState
     * se recupera en onRestoreInstanceState
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState")
        Log.i(TAG, "Recuperar ${savedInstanceState.getInt(COUNTER_KEY)}")
        counterState = savedInstanceState.getInt(COUNTER_KEY)
    }

}
