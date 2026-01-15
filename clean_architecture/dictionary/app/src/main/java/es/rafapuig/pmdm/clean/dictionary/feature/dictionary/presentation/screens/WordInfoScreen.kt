package es.rafapuig.pmdm.clean.dictionary.feature.dictionary.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.clean.dictionary.feature.dictionary.presentation.WordInfoUiState
import es.rafapuig.pmdm.clean.dictionary.feature.dictionary.presentation.components.WordInfoItem

@Composable
fun WordInfoScreen(
    state: WordInfoUiState,
    onQueryChange: (String) -> Unit = {},
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            TextField(
                value = state.query,
                onValueChange = onQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Search...")
                }
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                itemsIndexed(state.wordInfoItems) { index, wordInfo ->
                    WordInfoItem(wordInfo = wordInfo)
                    if(index < state.wordInfoItems.size - 1) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }

}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun WordInfoScreenPreview() {
    WordInfoScreen(
        state = WordInfoUiState()
    )
}