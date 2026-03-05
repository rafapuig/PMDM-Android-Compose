package es.rafapuig.pmdm.compose.learning.components.text.style

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TextStyleByCopyDemo1() {

    val textStyle = LocalTextStyle.current

    Text(
        "Hola Compose",
        style = textStyle.copy(
            color = Color.Red,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
    )
}

@Composable
fun TextStyleByCopyDemo2() {

    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current
            .copy(color = Color.Green)
    ) {
        Text("Hola Compose")
    }

    CompositionLocalProvider(
        LocalTextStyle provides
                TextStyle(color = Color.Cyan, fontStyle = FontStyle.Italic)
    ) {
        Text("Hola Compose")

        /**
         * De esta manera se pierde la cursiva
         */
        CompositionLocalProvider(LocalTextStyle provides
                TextStyle(color = Color.Blue)) {
            Text("Hola Compose")
        }

        /**
         * De esta manera no se pierde la cursiva
         * dado que se parte del estilo aplicado en el scope
         * y se crea una copia modificada en la propiedad color
         */
        CompositionLocalProvider(LocalTextStyle provides
                LocalTextStyle.current.copy(color = Color.Blue)) {
            Text("Hola Compose")
        }
    }
    Text("Hola Compose")
}

@Preview
@Composable
fun TextStyleByCopyDemoPreview() {
    Column {
        TextStyleByCopyDemo1()
        TextStyleByCopyDemo2()
    }
}