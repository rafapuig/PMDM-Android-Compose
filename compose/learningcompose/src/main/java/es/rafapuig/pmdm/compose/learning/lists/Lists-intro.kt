package es.rafapuig.pmdm.compose.learning.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ColumnListDemoScreen(modifier: Modifier = Modifier) {
    Column {
        repeat(8) {
            Text(text = "Item $it")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowListDemoScreen(modifier: Modifier = Modifier) {
    Row {
        repeat(5) {
            Text(text = "Item $it")
        }
    }
}