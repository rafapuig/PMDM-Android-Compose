package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
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
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.R


@Composable
fun TextStyleDemo() {
    val textStyle = LocalTextStyle.current

    val indieFlower = Font(R.font.indie_flower)

    val mergeStyle = TextStyle(
        color = Color.Blue,
        fontSize = 50.sp,
        /**
         * El grosor de la fuente puede ir entre 1 y 1000
         */
        fontWeight = FontWeight(1), //FontWeight.ExtraBold
        fontStyle = FontStyle.Italic, // o Normal
        fontSynthesis = FontSynthesis.All,
        fontFamily = FontFamily(indieFlower),
        fontFeatureSettings = null,
        letterSpacing = 10.sp, // espacio entre letras
        baselineShift = BaselineShift.None, //SuperScript o SubScript
        textGeometricTransform = TextGeometricTransform(
            scaleX = 2.0f, // anchura horizotal escalada
            skewX = 0.8f, // Inclinación a izquierda +  a derecha -
        ),
        localeList = null,
        //background = Color.Yellow,
        textDecoration = TextDecoration.Underline + TextDecoration.LineThrough,
        shadow = Shadow(
            color = Color.DarkGray,
            offset = Offset(16f, 16f),
            blurRadius = 6.0f,
        ),
        /**
         * Hay que importar la biblioteca drawing de compose
         */
        drawStyle = Stroke(5.0f), // o Fill
        textAlign = TextAlign.Center,
        //textDirection = TextDirection.Unspecified,
        lineHeight = 100.sp, //espaciado entre lineas
        textIndent = TextIndent(100.sp),
        /**
         * Este parametro no tiene utilidad
         */
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Proportional,
            trim = LineHeightStyle.Trim.Both,
            //modifer = LineHeightStyle.Modifier.Default
        ),
        lineBreak = LineBreak.Simple,
        hyphens = Hyphens.Auto, // el - para partir palabras al final de linea
        textMotion = TextMotion.Animated
    )

    ProvideTextStyle(mergeStyle) {
        Text("Hola Styled Android Jetpack Compose!!!")
    }
}

//@Preview(showSystemUi = true)
@Composable
fun TextStyleDemoPreview() {
    Scaffold { paddingValues ->
        Box(Modifier.padding(paddingValues).fillMaxSize()) {
            TextStyleDemo()
        }
    }
}