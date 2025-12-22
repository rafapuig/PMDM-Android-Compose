package es.rafapuig.pmdm.compose.learning.layouts.rowcolumn.spacing

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
import es.rafapuig.pmdm.compose.learning.layouts.rowcolumn.TextCell


@Preview(showBackground = true)
@Composable
fun RowSpaceArrangementDemoScreen(
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

class SpaceArrangementProvider : PreviewParameterProvider<Arrangement.HorizontalOrVertical> {
    override val values: Sequence<Arrangement.HorizontalOrVertical>
        get() = sequenceOf(
            Arrangement.SpaceEvenly,
            Arrangement.SpaceBetween,
            Arrangement.SpaceAround
        )
}

@Preview(showBackground = true)
@Composable
fun RowSpaceArrangementDemoPreview(
    @PreviewParameter(SpaceArrangementProvider::class)
    horizontalArrangement: Arrangement.Horizontal
) {
    RowSpaceArrangementDemoScreen(
        modifier = Modifier,
        horizontalArrangement = horizontalArrangement
    )
}

@Preview(showBackground = true)
@Composable
fun ColumnSpaceArrangementDemoScreen(
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


@Preview(showBackground = true)
@Composable
fun ColumnSpaceArrangementDemoPreview(
    @PreviewParameter(SpaceArrangementProvider ::class)
    verticalArrangement: Arrangement.HorizontalOrVertical
) {
    ColumnSpaceArrangementDemoScreen(
        modifier = Modifier,
        verticalArrangement = verticalArrangement
    )
}