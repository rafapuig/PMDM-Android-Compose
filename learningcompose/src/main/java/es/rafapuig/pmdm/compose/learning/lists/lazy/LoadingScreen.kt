package es.rafapuig.pmdm.compose.learning.lists.lazy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.R

/**
 * En la preview puedes elegir la localización del dispositivo.
 * El texto por defecto es en inglés, pero puedes cambiarlo a español.
 */
@Preview(showSystemUi = true, locale = "es")
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier.fillMaxWidth(.5f).aspectRatio(1f)
        ) {
            CircularProgressIndicator(
                Modifier.fillMaxSize(),
                strokeWidth = 20.dp
            )
        }
        Spacer(Modifier.size(32.dp))
        Text(
            stringResource(R.string.loading_football_teams),
            Modifier.wrapContentHeight(align = Alignment.CenterVertically),
            fontSize = 32.sp
        )
    }
}