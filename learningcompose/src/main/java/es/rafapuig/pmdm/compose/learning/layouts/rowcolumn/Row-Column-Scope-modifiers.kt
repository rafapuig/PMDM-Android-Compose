package es.rafapuig.pmdm.compose.learning.layouts.rowcolumn

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * Row y Column
 *
 * Son funciones de orden superior
 * que declaran un parametro "content" de tipo función con receptor (de extensión)
 * Cuando se usa una lambda como argumento para el parámetro content
 * la lambda puede acceder mediante this al receptor de la lambda
 * El tipo de función para Row es RowScope.() -> Unit
 * Para Column es ColumnScope.() -> Unit
 *
 * RowScope proporciona los modificadores:
 * - align
 * - alignBy
 * - alignByBaseline
 * - paddingFrom
 * - weight (peso de un hijo relativo al peso de los hermanos)
 *
 * ColumnScope proporciona solamente los modificadores:
 * - align
 * - alignBy
 * - weight
 */

@Preview(showBackground = true)
@Composable
fun RowScopeAlignModifierDemoScreen(modifier: Modifier = Modifier) {
    Row(modifier.height(300.dp)) {
        TextCell("1", Modifier.align(Alignment.Top))
        TextCell("2", Modifier.align(Alignment.CenterVertically))
        TextCell("3", Modifier.align(Alignment.Bottom))
    }
}

@Preview(showBackground = true)
@Composable
fun RowScopeBaseLineModifierDemoScreen(modifier: Modifier = Modifier) {
    Row {
        Text("Grande",
            Modifier.alignByBaseline(),
            fontSize = 40.sp
        )
        Text("Pequeño",
            Modifier.alignByBaseline(),
            fontSize = 32.sp
        )
    }
  }

@Preview(showBackground = true)
@Composable
fun RowScopeWeightModifierDemoScreen(modifier: Modifier = Modifier) {
    Row {
        TextCell("1", Modifier.weight(.25f))
        TextCell("2", Modifier.weight(.5f))
        TextCell("3", Modifier.weight(.25f))
    }
}