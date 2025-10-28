package es.rafapuig.pmdm.compose.learning.lists.lazy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.pastel.PMDMComposeTheme


@Composable
fun LazyColumnListDemo(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        /**
         * función item para crear un elemento (en LazyListScope)
         */
        item { Text(text = "Header", fontWeight = FontWeight.Bold) }
        /**
         * función items para crear varios elementos (en LazyListScope)
         */
        items(10) {
            /**
             * en un LazyItemScope con it de tipo Int como indice del elemento
             */
            Text(text = "Item $it")
        }

        val colorNames = listOf("Red", "Green", "Blue")

        items(colorNames) {
            Text(text = "Color: $it")
        }

        stickyHeader { Text("header sticky $it") }

        item { Text("Items con indice y valor...") }

        itemsIndexed(colorNames) { index, item ->
            Text(text = "Item $index: $item")
        }

        /**
         * Añadimos muchos más items para hacer scroll
         */
        items(100) {
            Text(text = "Item adicional $it")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LazyColumnListDemoPreview() {
    PMDMComposeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            LazyColumnListDemo(Modifier.padding(it))
        }
    }
}