package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

@Preview
@Composable
fun Demo() {
    var red by remember { mutableFloatStateOf(0f) }

    /**
     * Un LaunchedEffect no se ejecuta en la composición y en cada recomposición
     * Solamente se ejecuta cuando cambia el valor de la key
     * (si ponemos Unit la clave nunca cambia y por tanto solo se ejecuta una vez)
     */
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            var increment = 0.025f
            while (isActive) {
                delay(100.milliseconds)
                if (red > 1f) increment *= -1f
                if (red < 0f) increment *= -1f
                red += increment // Cambio en estado provoca recomposición
                Log.i("PMDM", "Intensidad de rojo: $red")
            }
        }
    }

    Box(// Depende de red, se recompone cuando cambia
        modifier = Modifier
            .fillMaxSize()
            .background(Color(red = red, 0f, 0f))
    )

}