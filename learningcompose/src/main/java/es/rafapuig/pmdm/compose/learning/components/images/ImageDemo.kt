package es.rafapuig.pmdm.compose.learning.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.R
import es.rafapuig.pmdm.compose.learning.modifiers.ModifiersDemoScreenPreview

class ContentScaleProvider() : PreviewParameterProvider<ContentScale> {
    override val values: Sequence<ContentScale>
        get() = sequenceOf(
            ContentScale.Fit, // Se ajusta al destino (wh < dest)
            ContentScale.Crop, // Se ajusta a la dimensión más larga
            ContentScale.None, // No se ajusta
            ContentScale.Inside, // Solo se ajusta si es mas grande que el destino
            ContentScale.FillHeight, // Se ajusta en alto (puede recortar por lados si es muy ancha)
            ContentScale.FillWidth, // Se ajusta en ancho (puede recortar por arriba y abajo si el muy larga)
            ContentScale.FillBounds // Se ajusta al destino (no mantiene relación aspecto)
        )
}

@Preview(showSystemUi = true)
@Composable
fun ImageContentScaleDemoScreen(
    @PreviewParameter(ContentScaleProvider::class)
    contentScale: ContentScale) {

    val painter = painterResource(id = R.drawable.real_sociedad)


    Column {
        val boxModifier =
            Modifier
                .border(2.dp, Color.Black)
                .fillMaxWidth()
        val imageAlignment = Alignment.Center // Cambiar para ver efecto

        Box(modifier = boxModifier.weight(1f)) {
            Image(
                painter = painter,
                contentDescription = "Escudo",
                modifier = Modifier.fillMaxSize(),
                contentScale = contentScale,
                alignment = imageAlignment
            )
        }

        Box(modifier = boxModifier.weight(3f)) {
            Image(
                painter = painter,
                contentDescription = "Escudo",
                modifier = Modifier.fillMaxSize(),
                contentScale = contentScale,
                alignment = imageAlignment
            )
        }
    }

}