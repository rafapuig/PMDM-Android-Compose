package es.rafapuig.pmdm.previewsdemo

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.previewsdemo.ui.theme.PMDMComposeTheme


@Composable
fun MyButton(
    text: String, // parámetro obligatorio
    width: Dp = 200.dp, // parámetro opcional con valor por defecto 200.dp
    color: Color = Color.Yellow // parámetro opcional con valor por defecto Color.Yellow
) {
    Text(
        text,
        Modifier
            .clip(RoundedCornerShape(30))
            .background(color)
            .padding(16.dp)
            .width(width)
            .clickable {},
        textAlign = TextAlign.Center,
        color = Color.Blue
    )
}

/**
 * Preview para ver el composable mientras se escribe el código
 * de la función MyButton
 * En una previa de un composable componente para una pantalla
 * normalmente utilizaremos el atributo showBackground = true
 */
@Preview(showBackground = true)
@Composable
fun MyButtonPreview1() {
    MyButton("Hola Compose")
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview2() {
    MyButton("Hola Compose", 300.dp)
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview3() {
    MyButton("Hola Compose", 250.dp, Color.Magenta)
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview4() {
    MyButton("Hola Compose", color = Color.Cyan)
}

@Composable
fun DemoScreenContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MyButton("Hola Compose", 300.dp)
        MyButton("Adios Compose", color = Color.Magenta)
    }
}


/**
 * Si lo que previsualizamos es el contenido
 * de una pantalla también usamos el showBackground
 */
@Preview(showBackground = true)
@Composable
fun DemoScreenContentPreview() {
    DemoScreenContent()
}

/**
 * Si previsualizamos una pantalla completa
 * Podemos usar el showSystemUi = true
 * y elegir el tema claro u oscuro
 * Para ellos hay que usar la función del tema de Material
 */
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DemoScreenPreview() {
    PMDMComposeTheme {
        /**
         * El parámetro content del Scaffold es una lambda que recibe
         * un PaddingValues con el padding qu debemos aplicar
         * a los elementos de la pantalla para que dejen espacio
         * a los elementos de la UI del sistema
         */
        Scaffold { innerPadding ->
            DemoScreenContent(
                Modifier.padding(innerPadding)
            )
        }
    }
}