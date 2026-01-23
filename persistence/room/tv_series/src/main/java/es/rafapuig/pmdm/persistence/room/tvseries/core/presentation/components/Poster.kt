package es.rafapuig.pmdm.persistence.room.tvseries.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import coil3.compose.rememberAsyncImagePainter

enum class PosterSize(val value: String) {
    W92("w92"),
    W154("w154"),
    W185("w185"),
    W342("w342"),
    W500("w500"),
    W780("w780"),
    ORIGINAL("original")
}

private const val BASE_URL = "https://image.tmdb.org/t/p/"

@Composable
fun Poster(
    posterPath: String,
    size: PosterSize,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    val url = "$BASE_URL${size.value}$posterPath"

    println("url: $url")

    val isPreview = LocalInspectionMode.current

    val painter =
        //if (isPreview) {
            //painterResource(R.drawable.ratatouille)
        //} else
            rememberAsyncImagePainter(model = url)

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.aspectRatio(500f / 750f),
        contentScale = ContentScale.FillBounds
    )
}


class PosterPathProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf(
            "/nGUelOVetiRpY2wTBMHTbrTIGYC.jpg"
        )
}


@Preview
@Composable
fun PosterPreview(
    @PreviewParameter(PosterPathProvider::class) posterPath: String
) {
    Poster(
        posterPath = posterPath,
        size = PosterSize.W500
    )
}