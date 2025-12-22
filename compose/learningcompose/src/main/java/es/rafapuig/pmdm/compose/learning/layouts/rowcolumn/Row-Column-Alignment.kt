package es.rafapuig.pmdm.compose.learning.layouts.rowcolumn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp


/**
 * El layout Row
 * alinea a sus hijos en la esquina superior izquierda.
 *
 * El alineamiento en vertical del layout Row
 * se modifica usando el parámetro verticalAlignment.
 */



@Preview(showBackground = true)
@Composable
fun RowAlignmentDemoScreen(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
) {
    Row(
        modifier.size(400.dp, 200.dp),
        verticalAlignment = verticalAlignment // Probar otros (Bottom, Top)
    ) {
        TextCell("1")
        TextCell("2")
        TextCell("3")
    }
}

class RowVerticalAlignmentProvider : PreviewParameterProvider<Alignment.Vertical> {
    override val values = sequenceOf(
        Alignment.Top,
        Alignment.CenterVertically,
        Alignment.Bottom
    )
}

@Preview(showBackground = true)
@Composable
fun RowAlignmentDemoScreenPreview(
    @PreviewParameter(RowVerticalAlignmentProvider::class)
    verticalAlignment: Alignment.Vertical
) {
    RowAlignmentDemoScreen(Modifier, verticalAlignment)
}

/**
 * En el layout Column,
 *
 * El alineamiento en horizontal del layout Column
 * se modifica usando el parámetro horizontalAlignment.
 */

@Preview(showBackground = true)
@Composable
fun ColumnAlignmentDemoScreen(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    Column(
        modifier.size(200.dp, 400.dp),
        horizontalAlignment = horizontalAlignment // Probar otros (Start, End)
    ) {
        TextCell("1")
        TextCell("2")
        TextCell("3")
    }
}

class ColumnHorizontalAlignmentProvider : PreviewParameterProvider<Alignment.Horizontal> {
    override val values = sequenceOf(
        Alignment.Start,
        Alignment.CenterHorizontally,
        Alignment.End
    )
}

@Preview(showBackground = true)
@Composable
fun ColumnAlignmentDemoScreenPreview(
    @PreviewParameter(ColumnHorizontalAlignmentProvider::class)
    horizontalAlignment: Alignment.Horizontal
) {
    ColumnAlignmentDemoScreen(Modifier, horizontalAlignment)
}
