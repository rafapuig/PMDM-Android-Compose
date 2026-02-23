package es.rafapuig.pmdm.compose.learning.components.text.font.family

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import es.rafapuig.pmdm.compose.learning.R

@Composable
fun FontFamilyDemo(
    fontFamilyName: String,
    fontFamily: FontFamily
) {
    Text(
        "Hola fuente $fontFamilyName!!!",
        fontFamily = fontFamily
    )
}

class FontFamilyProvider
    : PreviewParameterProvider<Pair<String, FontFamily>> {

    override val values: Sequence<Pair<String, FontFamily>>
        get() = sequenceOf(
            "indie_flower" to R.font.indie_flower,
            "aclonica" to R.font.aclonica,
            "aladin" to R.font.aladin
        ).map { fontFamilyInfo ->
            val (name, fontId) = fontFamilyInfo
            name to FontFamily(
                Font(
                    fontId,
                    FontWeight.Normal
                )
            )
        }

}

@Preview(showBackground = true)
@Composable
fun FontFamilyDemoPreview(
    @PreviewParameter(FontFamilyProvider::class)
    fontFamilyInfo: Pair<String, FontFamily>
) {
    FontFamilyDemo(
        fontFamilyInfo.first,
        fontFamilyInfo.second
    )
}