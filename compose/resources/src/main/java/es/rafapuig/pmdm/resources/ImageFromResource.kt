package es.rafapuig.pmdm.resources

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toBitmapOrNull


@Composable
fun ImageFromResource() {

    /**
     * Para obtener en Compose imágenes desde los recursos se usa
     * la función painterResource() proporcionando en identificador del recurso
     * Para obtener el identificador hacemos uso de la clase R
     */
    val painter = painterResource(R.drawable.googlelogo_color_272x92dp)

    /**
     * Desde el contexto también podemos obtener la imagen como un drawable
     */
    val context = LocalContext.current
    val drawableFromContext = context
        .getDrawable(R.drawable.googlelogo_color_272x92dp)

    /**
     * Y desde un resource manager,
     * pero tendremos que proporcionar además el tema
     */
    val resources = LocalResources.current
    val drawableFromResources = resources.getDrawable(
        R.drawable.googlelogo_color_272x92dp,
        context.theme
    )

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {


        /**
         * El painter es la forma preferible en Compose
         * Se usa el tamaño intrínseco del painter
         * Como es un JPG se respeta la densidad de píxeles
         */
        Image(
            painter = painter,
            contentDescription = "Google"
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
        val bitmapFromContext = drawableFromContext?.toBitmapOrNull(544, 184)
        bitmapFromContext?.let {
            Log.i("SIZE", "Context bitmap size: ${it.width}x${it.height}")
            Log.i("DENSITY", "Context bitmap density: ${it.density}")
        }

        bitmapFromContext?.asImageBitmap()?.let {
            Image(
                bitmap = it,
                contentDescription = "Google",
                //modifier = Modifier.height((bitmapFromContext.height / bitmapFromContext.density).dp)
            )
        }

        Image(
            bitmap = drawableFromResources
                .toBitmap(544, 184)
                .asImageBitmap(),
            contentDescription = "Google"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ImageFromResourceDefaultPreview() {
    ImageFromResource()
}