package es.rafapuig.pmdm.compose.exercises.lists.exercise1

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.exercises.R
import es.rafapuig.pmdm.compose.exercises.ui.theme.ComposeExercisesTheme

@Composable
fun MoviesGrid() {
    val widthPx = 500
    val density = LocalDensity.current
    val width = remember { with(density) { widthPx.toDp() } }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(30) {
            MovieCard(title = "El señor de los anillos: El retorno del rey",
            modifier = Modifier.padding(8.dp))
        }

    }
}

@Composable
fun MovieCard(
    title:String,
    modifier: Modifier = Modifier
) {
    val widthPx = 500
    val density = LocalDensity.current
    val width = remember { with(density) { widthPx.toDp() } }

    Card(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.nguelovetirpy2wtbmhtbrtigyc),
            contentDescription = null,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(100.dp)
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MoviesGridPreview() {
    ComposeExercisesTheme {
        Surface {
            MoviesGrid()
        }
    }
}