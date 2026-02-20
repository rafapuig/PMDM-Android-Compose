package es.rafapuig.pmdm.resources.locale

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import es.rafapuig.pmdm.resources.R

/**
 * Una de las ventajas de usar imagens en recursos
 * Es la de poder localizar según el idioma del dispositivo
 * que esté utilizando la aplicación
 *
 * El código de la función que usa el recurso de tipo string es
 * transparente al idioma en el que se ejecute la aplicación
 */
@Composable
fun ImageFromResourceLocale() {

    val painter = painterResource(R.drawable.schindler_list)

    val context = LocalContext.current
    val drawableFromContext = context.getDrawable(R.drawable.schindler_list)

    val resources = LocalResources.current
    val drawableFromResources = resources.getDrawable(R.drawable.schindler_list, context.theme)

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {


        /**
         * Se usa el tamaño intrínseco del painter
         * Como es un JPG se respeta la densidad de píxeles
         */
        Image(
            painter = painter,
            contentDescription = "Schindler List"
        )
        Log.i(
            "SIZE", "Painter intrinsic size : " +
                    "${painter.intrinsicSize}"
        )


        /**
         * El drawable pierde la información del tamaño
         */
        drawableFromContext?.let {
            Log.i("SIZE", "Context drawable size: ${it.intrinsicWidth}x${it.intrinsicHeight}")
        }

        /**
         * Aquí al llamar a toBitmap se puede crear un Bitmap con tamaño diferente
         * El bitmap trabaja con píxeles reales
         * Se pierde la información de densidad de píxeles (se usa la del dispositivo)
         */
        val bitmapFromContext = drawableFromContext?.toBitmapOrNull(500,750)
        bitmapFromContext?.let {
            Log.i("SIZE", "Context bitmap size: ${it.width}x${it.height}")
            Log.i("DENSITY", "Context bitmap density: ${it.density}")
        }

        bitmapFromContext?.asImageBitmap()?.let {
            Image(
                bitmap = it,
                contentDescription = "Schindler List",
                //modifier = Modifier.height((bitmapFromContext.height / bitmapFromContext.density).dp),
                contentScale = ContentScale.Fit
            )
        }

        Image(
            bitmap = drawableFromResources
                .toBitmap(500,750)
                .asImageBitmap(),
            contentDescription = "Schindler List"
        )
    }
}

/**
 * En las previas podemos elegir el idioma de configuración del dispositivo
 * en el que queremos ver la previa mediante el atributo locale
 */

/**
 * Previa con localización por defecto
 */
@Preview(showBackground = true)
@Composable
fun ImageFromResourceLocaleDefaultPreview() {
    ImageFromResourceLocale()
}


/**
 * Previa con localización en Español
 */
@Preview(showBackground = true, locale = "es")
@Composable
fun ImageFromResourceLocaleESPreview() {
    ImageFromResourceLocale()
}