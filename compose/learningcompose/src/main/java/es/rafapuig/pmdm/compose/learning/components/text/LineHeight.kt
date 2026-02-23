package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, widthDp = 100)
@Composable
fun TextLineHeightDemo() {
    Column {
        /**
         * Con valor 0 no imprime el texto
         */
        Text("Line height 0", lineHeight = 0.sp)
        HorizontalDivider()
        Text("Line height 5", lineHeight = 5.sp)
        HorizontalDivider()
        Text("Line height 10", lineHeight = 10.sp)
        HorizontalDivider()
        Text("Line height 20", lineHeight = 20.sp)
        HorizontalDivider()
        Text("Line height 40", lineHeight = 40.sp)
        HorizontalDivider()
        Text("Line height 50", lineHeight = 50.sp)
        HorizontalDivider()
        Text("Line height 80", lineHeight = 80.sp)
        HorizontalDivider()
        Text("Line height 100", lineHeight = 100.sp)
    }
}
