package es.rafapuig.pmdm.compose.exercises.side_effects

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.exercises.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

/**
 * Crea una pantalla donde el usuario vea un contador que hace una cuenta
 * regresiva empezando por un valor inicial
 * Tienes que crear un composable que se encargue de la logica del
 * conteo regresivo y se actualize mostrando el valor actual de la cuenta
 *
 * Si el composable se invisibiliza debe pararse la cuenta atras
 * (no puedes usar LauchEffect ni rememberCoroutineScope)
 */


private fun counterFlow(from: Int = 0) =
    (from downTo 0).asFlow().transform {
        emit(it)
        delay(1.seconds)
    }


@Composable
fun CounterDownDisposable(
    from: Int = 10
) {
    var counter by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth(.5f)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10))
            .background(Color.DarkGray)
            .border(4.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(10)),
        contentAlignment = Alignment.Center
    ) {
        Text(counter, style = MaterialTheme.typography.displayLarge)
    }

    /**
     * Como el ejericio pide que no se use rememberCoroutineScope
     * tenemos que crear el scope a mano nosotros
     * y recordarlo entre recomposiciones
     */
    val scope = remember { CoroutineScope(Dispatchers.IO) }

    /**
     * Como la corrutina la lanzamos mediante
     * un scope que no está ligado al comosable
     * no se cancelará automaticamente cuando el composable salga
     * de la composición (ver mas adelante)
     * porque ese scope sobrevive al composable
     */
    DisposableEffect(from) {

        val job = scope.launch {
            counterFlow(from)
                .onEach { counter = it.toString() }
                .collect {
                    println("Counter: $it")
                }
            counter = ""
        }

        /**
         * Disposable effect cuenta con un método que definimas
         * para indicar que hacer cuando el composable se sale de la composición
         */
        onDispose {
            /**
             * En este caso, cancelar la corrutina que recolecta el flow
             * Prueba a comentar esta linea para ver que el flow sigue recolectando
             * y no se cancela
             */
            job.cancel()
        }
    }

}

@OptIn(FlowPreview::class)
@Composable
fun CounterDownDisposableDemo(
    modifier: Modifier = Modifier
) {
    println("Recomposición")


    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text("Cuenta reversiva")

        var toggle by remember { mutableStateOf(false) }

        /**
         * Segun el valor del toggle
         * CounterDownDisposable entrará o saldrá de la composición
         */
        if (toggle) CounterDownDisposable(10)

        Spacer(modifier = Modifier.height(24.dp))

        Switch(
            checked = toggle,
            onCheckedChange = {
                toggle = it
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CounterDownDisposableDemoPreview() {
    PMDMComposeTheme {
        Scaffold { innerPadding ->
            CounterDownDisposableDemo(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
