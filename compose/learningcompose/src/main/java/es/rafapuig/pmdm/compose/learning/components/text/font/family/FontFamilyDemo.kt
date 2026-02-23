package es.rafapuig.pmdm.compose.learning.components.text.font.family

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.R

@Composable
fun FontFamilyDemo() {

    val fontFamily = FontFamily(
        Font(
            R.font.aclonica,
            FontWeight.Normal
        )
    )

    Text("Hola Compose!!!", fontFamily = fontFamily)
}

@Preview(showBackground = true)
@Composable
fun FontFamilyDemoPreview() {
    FontFamilyDemo()
}