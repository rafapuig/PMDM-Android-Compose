package es.rafapuig.pmdm.resources.locale

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.resources.R

/**
 * Una de las ventajas de usar textos en recursos en strings.xml
 * Es la de poder localizar los textos según el idioma del dispositivo
 * que esté utilizando la aplicación
 * Podemos definir el texto traducido a diferentes idiomas en strings.xml
 *
 * El código de la función que usa el recurso de tipo string es
 * transparente al idioma en el que se ejecute la aplicación
 */
@Composable
fun GreetingFromStringResourceLocale() {

    val text = stringResource(R.string.greeting)

    val context = LocalContext.current
    val textFromContext = context.getString(R.string.greeting)

    val resources = LocalResources.current
    val textFromResources = resources.getString(R.string.greeting)

    Column {
        Text(
            text = text
        )
        Text(
            text = textFromContext
        )
        Text(
            text = textFromResources
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
fun GreetingFromStringResourceLocaleDefaultPreview() {
    GreetingFromStringResourceLocale()
}

/**
 * Previa con localización en Catalán
 */
@Preview(showBackground = true, locale = "ca")
@Composable
fun GreetingFromStringResourceLocaleCAPreview() {
    GreetingFromStringResourceLocale()
}

/**
 * Previa con localización en Inglés
 */
@Preview(showBackground = true, locale = "en")
@Composable
fun GreetingFromStringResourceLocaleENPreview() {
    GreetingFromStringResourceLocale()
}

/**
 * Previa con localización en Español
 */
@Preview(showBackground = true, locale = "es")
@Composable
fun GreetingFromStringResourceLocaleESPreview() {
    GreetingFromStringResourceLocale()
}