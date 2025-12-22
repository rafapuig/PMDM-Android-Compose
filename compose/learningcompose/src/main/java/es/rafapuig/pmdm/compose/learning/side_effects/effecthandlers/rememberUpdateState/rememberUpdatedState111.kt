package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun CounterDisplay(counter: Int) {
    println("Componiendo CounterDisplay...")


    val isPositive1 = run {
        println("Calculando isPositive (1) ...")
        counter >= 0
    }

    val isPositive2 by remember {
        derivedStateOf {
            println("Calculando isPositive (2)... counter = $counter")
            counter >= 0
        }
    }

    val updatedCounter by rememberUpdatedState(newValue = counter)

    val isPositive3 by remember {
        derivedStateOf {
            println("Calculando isPositive (3) ... updatedCounter = $updatedCounter")
            updatedCounter >= 0
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(.8f),
        horizontalArrangement = Arrangement
            .spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(if (isPositive1) Color.Green else Color.Red)
                .weight(.33f)
                .aspectRatio(1f)
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(if (isPositive2) Color.Green else Color.Red)
                .weight(.33f)
                .aspectRatio(1f)
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(if (isPositive3) Color.Green else Color.Red)
                .weight(.33f)
                .aspectRatio(1f)
        )
    }


    Text(text = "$counter", fontSize = 128.sp)
}

@Preview(showBackground = true)
@Composable
fun RememberUpdateStateDemo111() {

    Scaffold {

        var counter by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement
                .spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            CounterDisplay(counter = counter)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement
                    .spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
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

            Button(
                onClick = { counter = 0 }
            ) {
                Text("Reiniciar")
            }

        }
    }
}
