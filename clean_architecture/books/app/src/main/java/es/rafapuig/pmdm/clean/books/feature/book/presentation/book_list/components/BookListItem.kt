package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.components

import android.hardware.lights.Light
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import es.rafapuig.pmdm.clean.books.R
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book
import es.rafapuig.pmdm.clean.books.ui.theme.LightBlue
import es.rafapuig.pmdm.clean.books.ui.theme.SandYellow
import kotlin.math.round

@Composable
fun BookListItem(
    book: Book,
    onClick: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (!isSystemInDarkTheme()) LightBlue.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surface
        ),
        modifier = modifier.clickable { onClick(book) }

    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            BookCoverImage(book)

            BookInfo(
                book,
                modifier = Modifier
                    .fillMaxHeight() // Dado que hemos limitado el Row con el IntrisicSize
                    .weight(1f) // Ocupará todo el espacio restante disponible en la Row
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
        }

    }

}

@Composable
private fun BookInfo(book: Book, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        // Titulo
        Text(
            text = book.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        // Autores
        book.authors.firstOrNull()?.let { authorName ->
            Text(
                text = authorName,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        book.averageRating?.let { rating ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${round(rating * 10) / 10.0}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = SandYellow
                )
            }
        }

    }
}

@Composable
private fun BookCoverImage(book: Book) {
    Box(
        modifier = Modifier.height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        var imageLoadResult by remember {
            mutableStateOf<Result<Painter>?>(null)
        }

        val painter = rememberAsyncImagePainter(
            model = book.imageUrl,
            onSuccess = {
                imageLoadResult =
                    if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                        Result.success(it.painter)
                    } else {
                        Result.failure(IllegalArgumentException("Invalid image size"))
                    }
            },
            onError = {
                it.result.throwable.printStackTrace()
                imageLoadResult = Result.failure(it.result.throwable)
            }
        )

        when (val result = imageLoadResult) {
            null -> CircularProgressIndicator()
            else -> {
                Image(
                    painter =
                        if (result.isSuccess) painter
                        else painterResource(id = R.drawable.book_error_2),
                    contentDescription = null,
                    contentScale = if (result.isSuccess) {
                        ContentScale.Crop
                    } else {
                        ContentScale.Fit
                    },
                    modifier = Modifier.aspectRatio(
                        0.65f,
                        matchHeightConstraintsFirst = true
                    )
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun BookListItemPreview() {
    BookListItem(
        book = Book(
            title = "Kotlin", authors = listOf("Rafael Puig", "Author 2"),
            averageRating = 4.555
        ),
        onClick = {}
    )
}