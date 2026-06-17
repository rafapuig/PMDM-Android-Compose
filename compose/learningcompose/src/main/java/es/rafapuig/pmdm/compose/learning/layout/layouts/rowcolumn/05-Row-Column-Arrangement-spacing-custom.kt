package es.rafapuig.pmdm.compose.learning.layout.layouts.rowcolumn

import androidx.compose.foundation.layout.Arrangement
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


@Composable
fun RowCustomSpaceArrangementDemo(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(5.dp)
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

@Preview(showBackground = true)
@Composable
fun RowCustomSpaceArrangementDemoPreview() {
    RowCustomSpaceArrangementDemo()
}

@Preview(showBackground = true)
@Composable
fun RowCustomSpaceArrangementDemoPreview2() {
    RowCustomSpaceArrangementDemo(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    )
}

@Preview(showBackground = true)
@Composable
fun RowCustomSpaceArrangementDemoPreview3() {
    RowCustomSpaceArrangementDemo(
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.End)
    )
}

@Composable
fun ColumnCustomSpaceArrangementDemo(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(5.dp)
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

@Preview(showBackground = true)
@Composable
fun ColumnCustomSpaceArrangementDemoPreview() {
    ColumnCustomSpaceArrangementDemo()
}

@Preview(showBackground = true)
@Composable
fun ColumnCustomSpaceArrangementDemoPreview2() {
    ColumnCustomSpaceArrangementDemo(
        verticalArrangement = Arrangement
            .spacedBy(10.dp, Alignment.CenterVertically)
    )
}

@Preview(showBackground = true)
@Composable
fun ColumnCustomSpaceArrangementDemoPreview3() {
    ColumnCustomSpaceArrangementDemo(
        verticalArrangement = Arrangement
            .spacedBy(20.dp, Alignment.Bottom)
    )
}