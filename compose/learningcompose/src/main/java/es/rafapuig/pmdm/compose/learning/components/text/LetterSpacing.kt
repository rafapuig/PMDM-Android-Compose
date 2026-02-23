package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun TextLetterSpacingDemo() {
    Column {
        Text("Letter spacing -1", letterSpacing = (-1).sp)
        Text("Letter spacing -0,5", letterSpacing = (-0.5).sp)
        Text("Letter spacing 0", letterSpacing = 0.sp)
        Text("Letter spacing 0,5", letterSpacing = 0.5.sp)
        Text("Letter spacing 1", letterSpacing = 1.sp)
        Text("Letter spacing 2", letterSpacing = 2.sp)
        Text("Letter spacing 4", letterSpacing = 4.sp)
        Text("Letter spacing 8", letterSpacing = 8.sp)
    }
}
