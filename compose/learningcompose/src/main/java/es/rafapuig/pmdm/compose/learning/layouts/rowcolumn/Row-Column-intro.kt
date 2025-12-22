package es.rafapuig.pmdm.compose.learning.layouts.rowcolumn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 500)
@Composable
fun RowLayoutDemoScreen(modifier: Modifier = Modifier) {
    Row(modifier) {
        TextCell("1")
        TextCell("2")
        TextCell("3")
        TextCell("4")
        TextCell("5")
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
fun ColumnLayoutDemoScreen(modifier: Modifier = Modifier) {
    Column (modifier) {
        TextCell("1")
        TextCell("2")
        TextCell("3")
        TextCell("4")
        TextCell("5")
    }
}

/**
 * Combinar Row y Column
 *
 * Podemos combinarlos para crear layouts en forma de cuadrícula
 */

@Preview(showBackground = true, widthDp = 300, heightDp = 400)
@Composable
fun GridLayoutDemoScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Row {
            TextCell("1")
            TextCell("2")
            TextCell("3")
        }
        Row {
            TextCell("4")
            TextCell("5")
            TextCell("6")
        }
        Row {
            TextCell("7")
            TextCell("8")

        }
        Row {
            TextCell("9")
            TextCell("10")
            TextCell("11")
        }
    }
}