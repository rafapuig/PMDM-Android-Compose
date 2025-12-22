package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun Counter1(
    step: Int = 0
) {
    var counter by remember {
        mutableIntStateOf(0)
    }

    Text(text = "$counter", fontSize = 128.sp)


    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            var internalCounter = 0
            while (true) {
                delay(500.milliseconds)
                counter = internalCounter
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RememberUpdateStateDemo1() {
    Scaffold {innerPadding ->

        var counter by remember { mutableIntStateOf(0) }

        val isDecrementEnabled by remember { derivedStateOf { counter > 0 } }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement
                .spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(text = "$counter", fontSize = 128.sp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement
                    .spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    enabled = isDecrementEnabled,
                    onClick = { counter -= 1 }
                ) {
                    Text("Decrementar")
                }
                Button(
                    onClick = { counter += 1 }
                ) {
                    Text("Incrementar")
                }
            }
        }
    }
}
