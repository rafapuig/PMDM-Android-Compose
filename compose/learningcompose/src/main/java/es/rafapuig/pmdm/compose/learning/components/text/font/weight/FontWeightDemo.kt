package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FontWeightDemo() {
    Text("Font weight Thin", fontWeight = FontWeight.Thin) // Alias de W100
    Text("Font weight ExtraLight", fontWeight = FontWeight.ExtraLight) // Alias de W200
    Text("Font weight Light", fontWeight = FontWeight.Light) // Alias de W300
    Text("Font weight Normal", fontWeight = FontWeight.Normal) // Alias de W400
    Text("Font weight Medium", fontWeight = FontWeight.Medium) // Alias de W500
    Text("Font weight SemiBold", fontWeight = FontWeight.SemiBold) // Alias de W600
    Text("Font weight Bold", fontWeight = FontWeight.Bold) // Alias de W700
    Text("Font weight ExtraBold", fontWeight = FontWeight.ExtraBold) // Alias de W800
    Text("Font weight Black", fontWeight = FontWeight.Black) // Alias de W900
}

@Composable
fun FontWeightDemo2() {
    Text("Font weight Thin", fontWeight = FontWeight.W100) // Alias de W100
    Text("Font weight ExtraLight", fontWeight = FontWeight.W200) // Alias de W200
    Text("Font weight Light", fontWeight = FontWeight.W300) // Alias de W300
    Text("Font weight Normal", fontWeight = FontWeight.W400) // Alias de W400
    Text("Font weight Medium", fontWeight = FontWeight.W500) // Alias de W500
    Text("Font weight SemiBold", fontWeight = FontWeight.W600) // Alias de W600
    Text("Font weight Bold", fontWeight = FontWeight.W700) // Alias de W700
    Text("Font weight ExtraBold", fontWeight = FontWeight.W800) // Alias de W800
    Text("Font weight Black", fontWeight = FontWeight.W900) // Alias de W900
}

@Preview(showBackground = true)
@Composable
fun FontWeightDemoPreview() {
    Column {
        FontWeightDemo()
    }
}

@Preview(showBackground = true)
@Composable
fun FontWeightDemoPreview2() {
    Column {
        FontWeightDemo2()
    }
}