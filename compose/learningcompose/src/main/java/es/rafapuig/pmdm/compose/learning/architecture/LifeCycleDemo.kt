package es.rafapuig.pmdm.compose.learning.architecture

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LifeCycleDemo() {
    Log.d("Composition", "LifeCycleDemo")

    val counter = remember { mutableIntStateOf(0) }

    Text("Has pulsado el botón ${counter.intValue} veces")

    Button(onClick = { counter.intValue++ }) {
        Text("Pulsar")
    }
}

@Preview
@Composable
fun LifeCycleDemoPreview() {
    LifeCycleDemo()
}