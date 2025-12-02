package es.rafapuig.pmdm.compose.lifecycle.save_UI_state

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.lifecycle.UiStateViewModel
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

/** https://developer.android.com/guide/components/activities/activity-lifecycle */

/**
 *  Para matar la actividad, ponerla en segundo plano pulsando Home del dispositivo
 *  Y luego ejecutar el siguiente comando:
 *
 *  adb shell am kill es.rafapuig.pmdm.compose.lifecycle
 *
 *  en la terminal de Android Studio
 */
class UIStateViewModelActivity : ComponentActivity() {

    private val TAG = "StateChangeActivity"

    val viewModel by viewModels<UiStateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {

                val counterState by viewModel.counterSavedFlow.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
                    ) {
                        val style = MaterialTheme.typography.headlineMedium
                        Text(text = "Normal counter: ${viewModel.counterNormalState}", style = style)
                        Text(text = "Saved  counter: $counterState", style = style)
                        Text(text = "State counter: ${viewModel.counterSavedState}", style = style)
                        Button(onClick = {
                            viewModel.onCounterNormalStateChange(viewModel.counterNormalState + 1)
                            viewModel.onCounterSavedFlowChange(counterState + 1)
                            viewModel.onCounterSavedStateChange(viewModel.counterSavedState + 1)
                            Log.i(TAG, "Incrementar $counterState")
                        }) {
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
