package es.rafapuig.pmdm.compose.learning.resources

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.R

@Composable
fun StringResourcesDemo() {
    Text(stringResource(R.string.hello_world))
}

@Preview(
    showBackground = true)
@Composable
fun DefaultPreview() {
    StringResourcesDemo()
}

@Preview(
    showBackground = true,
    locale = "es"
    )
@Composable
fun SpanishPreview() {
    StringResourcesDemo()
}