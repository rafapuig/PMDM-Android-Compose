package es.rafapuig.pmdm.navigation.navigation3.basic.presentation.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.navigation.navigation3.basic.domain.model.Item
import es.rafapuig.pmdm.navigation.navigation3.basic.domain.sampleItems

@Composable
fun ItemListScreen(
    items: List<Item>,
    onItemClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.systemBarsPadding(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            items(items) { item ->
                Text(
                    item.title,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.clickable { onItemClick(item.id) })
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ItemListScreenPreview() {
    ItemListScreen(
        sampleItems,
        onItemClick = {}
    )
}