package es.rafapuig.pmdm.compose.learning.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.R

@Preview(showBackground = true, backgroundColor = 0xFF2196F3)
@Composable
fun ImageClipDemoScreen() {
    Image(
        painter = painterResource(id = R.drawable.mujer_con_cabello_corto_rubio_bronceado_sonriendo_lindo_y_mirando_la_camara_con_amigable),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .size(300.dp)
            .clip(CircleShape)
            .border(
                width = 6.dp,
                shape = CircleShape,
                brush = Brush.linearGradient(
                    listOf(Color.Red, Color.Yellow, Color.Blue)
                )
            )
    )
}
