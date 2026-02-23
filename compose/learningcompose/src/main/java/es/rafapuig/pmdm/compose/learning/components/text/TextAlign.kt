package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme

@Preview(showBackground = true, widthDp = 200)
@Composable
fun TextAlignDemo() {
    Column {

        Text("Align Start", textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth())
        Text("Align Left", textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())

        Text("Align center", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

        Text("Align End", textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
        Text("Align Right", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())

        Text(
            "Align Justify (un texto para rellenar más lineas)",
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            "Align Unspecified (un texto para rellenar más lineas)",
            textAlign = TextAlign.Unspecified
        )
    }
}

/**
 * Aplicamos el tema de Material
 */
@Preview(showBackground = true, widthDp = 200)
@Composable
fun TextAlignDemoPreview() {
    PMDMComposeTheme {
        TextAlignDemo()
    }
}
