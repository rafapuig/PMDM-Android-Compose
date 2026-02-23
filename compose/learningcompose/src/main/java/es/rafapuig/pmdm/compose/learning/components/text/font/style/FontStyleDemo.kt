package es.rafapuig.pmdm.compose.learning.components.text.fontstyle

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FontStyleDemo() {
    Text("Font style Normal", fontStyle = FontStyle.Normal)
    Text("Font style Italic", fontStyle = FontStyle.Italic)
}

@Preview(showBackground = true)
@Composable
fun FontStyleDemoPreview() {
    Column {
        FontStyleDemo()
    }
}