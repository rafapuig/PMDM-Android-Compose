package es.rafapuig.pmdm.compose.learning.layouts.rowcolumn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

/**
 * Al contrario que la alineación
 * El arrangement controla la posición de los hijos
 * a lo largo del mismo eje que el contenedor
 * (horizontalmente para Row y verticalmente para Column)
 *
 * Para Row se establece mediante horizontalArrangement
 * Pra Column mediante verticalArrangement
 */

@Preview(showBackground = true)
@Composable
fun RowPositionArrangementDemoScreen(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start
) {
    Row(
        modifier.size(400.dp, 200.dp),
        horizontalArrangement = horizontalArrangement
    ) {
        TextCell("1")
        TextCell("2")
        TextCell("3")
    }
}

class RowPositionArrangementProvider : PreviewParameterProvider<Arrangement.Horizontal> {
    override val values: Sequence<Arrangement.Horizontal>
        get() = sequenceOf(
            Arrangement.Start,
            Arrangement.Center,
            Arrangement.End
        )
}

@Preview(showBackground = true)
@Composable
fun RowArrangementDemoPreview(
    @PreviewParameter(RowPositionArrangementProvider::class)
    horizontalArrangement: Arrangement.Horizontal
) {
    RowPositionArrangementDemoScreen(
        modifier = Modifier,
        horizontalArrangement = horizontalArrangement
    )
}

@Preview(showBackground = true)
@Composable
fun ColumnPositionArrangementDemoScreen(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top
) {
    Column(
        modifier.size(200.dp, 400.dp),
        verticalArrangement = verticalArrangement
    ) {
        TextCell("1")
        TextCell("2")
        TextCell("3")
    }
}

class ColumnPositionArrangementProvider : PreviewParameterProvider<Arrangement.Vertical> {
    override val values: Sequence<Arrangement.Vertical>
        get() = sequenceOf(
            Arrangement.Top,
            Arrangement.Center,
            Arrangement.Bottom
        )
}

@Preview(showBackground = true)
@Composable
fun ColumnArrangementDemoPreview(
    @PreviewParameter(ColumnPositionArrangementProvider ::class)
    verticalArrangement: Arrangement.Vertical
) {
    ColumnPositionArrangementDemoScreen(
        modifier = Modifier,
        verticalArrangement = verticalArrangement
    )
}