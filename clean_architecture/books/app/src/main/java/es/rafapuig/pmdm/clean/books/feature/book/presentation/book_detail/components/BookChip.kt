package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ChipColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.clean.books.ui.theme.LightBlue

enum class ChipSize {
    Small,
    Medium,
    Large
}

@Composable
fun BookChip(
    modifier: Modifier = Modifier,
    size: ChipSize = ChipSize.Medium,
    chipContent: @Composable () -> Unit
) {
    SuggestionChip(
        onClick = {},
        colors =
            SuggestionChipDefaults.suggestionChipColors(
                containerColor = LightBlue
            ),
        border = BorderStroke(1.dp, LightBlue),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.width(
            when (size) {
                ChipSize.Small -> 50.dp
                ChipSize.Medium -> 120.dp
                ChipSize.Large -> 200.dp
            }
        ),
        label = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                chipContent()
            }
        }
    )

}


@Preview(showBackground = true)
@Composable
fun BookChipPreview() {
    BookChip {
        Text("Chip")
    }
}


