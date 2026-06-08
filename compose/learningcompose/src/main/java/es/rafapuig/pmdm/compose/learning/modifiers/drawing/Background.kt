package es.rafapuig.pmdm.compose.learning.modifiers.drawing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.gestures.toPx

@Preview(showBackground = true)
@Composable
fun BackgroundModifierDemo1() {
    val modifier =
        Modifier.background(
            color = Color.Cyan,
            shape = RoundedCornerShape(25)
        )

    Box(modifier = modifier.size(200.dp))
}

@Preview(showBackground = true)
@Composable
fun BackgroundModifierDemo2() {
    val modifier =
        Modifier.background(
            brush = Brush.horizontalGradient(
                listOf(Color.Blue, Color.Green)
            ),
            shape = RoundedCornerShape(25),
            alpha = 0.35f
        )

    Box(modifier = modifier.size(200.dp))
}

@Preview(showBackground = true)
@Composable
fun AlphaModifierDemo1() {
    val modifier =
        Modifier
            .alpha(0.35f) // Hay que ponerlo antes para que afecte al resto
            .background(
                color = Color.Cyan,
                shape = RoundedCornerShape(25)
            )

    Box(modifier = modifier.size(200.dp))
}

@Preview(showBackground = true)
@Composable
fun ClipModifierDemo1() {
    val modifier =
        Modifier
            .clip(RoundedCornerShape(25)) // recortar antes
            .background(color = Color.Cyan)

    Box(modifier = modifier.size(200.dp))
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun ClipToBoundsExample() {
    Row(
        modifier = Modifier
            .height(250.dp)
            .width(420.dp)
    ) {

        val childBoxModifier = Modifier
            .wrapContentSize(
                align = Alignment.TopStart,
                unbounded = true
            ) // Hay que aplicarlo antes de size
            .size(150.dp)
            .offset(50.dp, 50.dp)
            .background(Color.Green.copy(alpha = 0.6f))

        val parentBoxModifier = Modifier
            .size(100.dp)
            .background(Color.Red)

        // CASO 1: SIN clipToBounds (El contenido se desborda)
        Box(
            modifier = parentBoxModifier
        ) {
            Box(modifier = childBoxModifier)
        }

        Spacer(modifier = Modifier.width(120.dp)) // Espacio para que no se encabalguen en la preview

        // CASO 2: CON clipToBounds (El contenido se corta en el borde)
        Box(
            modifier = parentBoxModifier
                .clipToBounds() // <-- Aquí se recorta el exceso para ajustar a los límites del padre
        ) {
            Box(modifier = childBoxModifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawBehindModifierDemo() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = Color.LightGray)
            .size(200.dp)
            .drawBehind(
                onDraw = {
                    drawLine(
                        color = Color.Red,
                        start = Offset(50.dp.toPx(), 50.dp.toPx()),
                        end = Offset(150.dp.toPx(), 150.dp.toPx()),
                        strokeWidth = 50f,
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = Color.Red,
                        start = Offset(150.dp.toPx(), 50.dp.toPx()),
                        end = Offset(50.dp.toPx(), 150.dp.toPx()),
                        strokeWidth = 50f,
                        cap = StrokeCap.Round
                    )
                }
            )
    ) {
        // El contenido va por encima de lo dibujado en el canvas
        Box(modifier = Modifier.size(40.dp).background(Color.Green))
    }
}

/**
 * Con drawWithContent
 * tenemos la "obligación" de llamar a una función: drawContent
 * para que se dibuje el contenido
 * Podemos llamarla en cualquier momento:
 * - al principio
 * - al final
 */
@Preview(showBackground = true)
@Composable
fun DrawWithContentModifierDemo() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = Color.LightGray)
            .size(200.dp)
            .drawWithContent(
                onDraw = {

                    /**
                     * Dibuja el contenido
                     * Si lo llamamos antes de todo se dibuja el contenido
                     * detrás del todo
                     */
                    drawContent()

                    drawLine(
                        color = Color.Red,
                        start = Offset(50.dp.toPx(), 50.dp.toPx()),
                        end = Offset(150.dp.toPx(), 150.dp.toPx()),
                        strokeWidth = 50f,
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = Color.Red,
                        start = Offset(150.dp.toPx(), 50.dp.toPx()),
                        end = Offset(50.dp.toPx(), 150.dp.toPx()),
                        strokeWidth = 50f,
                        cap = StrokeCap.Round
                    )

                    // Si llamamos a drawContent al final
                    // Se dibuja el contenido por delante del canvas
                    //drawContent()
                }
            )
    ) {
        // El contenido va por encima de lo dibujado en el canvas
        Box(modifier = Modifier.size(80.dp).background(Color.Green))
    }
}