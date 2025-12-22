package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.R
import es.rafapuig.pmdm.compose.learning.modifiers.ModifierOrderingDemoScreen

@Preview(showBackground = true)
@Composable
fun TextDemo() {
    Column {
        Text("Hola Compose!!")
        Text("Color Red!!", color = Color.Red)
        Text("Font size 18 sp", fontSize = 18.sp)
        Text("Font weight ExtraBold", fontWeight = FontWeight.ExtraBold)
        Text("Fuente Indie Flower", fontFamily = FontFamily(Font(R.font.indie_flower)))
        Text("Letter spacing", letterSpacing = 8.sp)
        Text("Text Decoration Underline", textDecoration = TextDecoration.Underline)
        Text("Text Decoration LineThrough", textDecoration = TextDecoration.LineThrough)
        Text("Text Decoration Both", textDecoration = TextDecoration.Underline + TextDecoration.LineThrough)
        Text("Align center", textAlign = TextAlign.Center, modifier = Modifier.width(190.dp))
        Text("Align End", textAlign = TextAlign.End, modifier = Modifier.width(190.dp))
        Text("Align Justify (texto para rellenar lineas)", textAlign = TextAlign.Justify, modifier = Modifier.width(190.dp))
        Text("Line height 30 sp", lineHeight = 30.sp)
        Text("Overflow Ellipsis (texto para rellenar)", overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.width(190.dp))
        Text("Middle Ellipsis (texto para rellenar)",  overflow = TextOverflow.MiddleEllipsis, maxLines = 1, modifier = Modifier.width(190.dp))

        Text("Como si hubiera espacio horizontal ilimitado", softWrap = false, modifier = Modifier.width(190.dp))
        Text("SoftWrap salta de linea cuando se acaba el espacio", softWrap = true, modifier = Modifier.width(190.dp))

        Text(
            text = "Hola Compose!!!",
            style = LocalTextStyle.current.copy(
                color = Color.Red
            )
        )

        /**
         * Aplicamos Composición Local
         * con el TextStyle guardado en el estado LocalTextStyle
         */
        ProvideTextStyle(TextStyle(color = Color.Green)) {
            Text("Hola Compose local styled!!!")

            ProvideTextStyle(TextStyle(fontStyle = FontStyle.Italic)) {
                Text("Adios Compose local styled!!!")
            }
        }
    }
}
