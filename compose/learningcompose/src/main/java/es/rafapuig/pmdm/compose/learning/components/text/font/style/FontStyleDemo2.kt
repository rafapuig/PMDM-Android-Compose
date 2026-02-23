package es.rafapuig.pmdm.compose.learning.components.text.fontstyle

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FontStyleDemo2(fontStyle: FontStyle) {
    Text("Font style $fontStyle", fontStyle = fontStyle)
}

@Preview(showBackground = true)
@Composable
fun FontStyleDemoPreview2() {
    Column {
        FontStyle.values().forEach {
            FontStyleDemo2(fontStyle = it)
        }
    }
}
