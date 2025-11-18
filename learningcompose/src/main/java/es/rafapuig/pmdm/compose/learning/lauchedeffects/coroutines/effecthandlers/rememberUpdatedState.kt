package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Preview(showBackground = true)
@Composable
fun RememberUpdateStateDemo() {

    val counters = remember {
        mutableStateListOf(0, 0)
    }

    var counterIndex by remember {
        mutableStateOf(0)
    }

    val currentCounterIndex by rememberUpdatedState(newValue = counterIndex)

    /**
     * No queremos reiniciar la corrutina si cambia el counterIndex
     */
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            while (true) {
                delay(500.milliseconds)
                counters[counterIndex]++
                //counters[currentCounterIndex]++
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("${counters[0]}", fontSize = 32.sp)
            Text("${counters[1]}", fontSize = 32.sp)
        }
        Switch(
            checked = counterIndex == 1,
            onCheckedChange = {checked ->
                counterIndex = if (checked) 1 else 0
            }
        )
    }

}
