package es.rafapuig.pmdm.compose.learning.concepts.state

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SwitchDemo() {
    /**
     * estado checked para el Switch
     */
    var checked by remember { mutableStateOf(false) }

    Switch(
        checked = checked, //usa el valor del estado checked para indicar como se ve el Switch
        onCheckedChange = { checked = it } // cambiar el valor de checked cuando se pulsa el Switch
    )
}