@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.MenuDestination
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.screens.components.GridMenuCard

@Composable
fun MenuScreenGrid(
    onNavigate: (MenuDestination) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Menú principal") }
            )
        }
    ) { padding ->

        val items = listOf(
            Triple("Sensores", Icons.Default.Sensors, MenuDestination.Sensors),
            Triple("Ajustes", Icons.Default.Settings, MenuDestination.Settings),
            Triple("Dados", Icons.Default.Casino, MenuDestination.Dice),
            Triple("Conversor", Icons.Default.SwapHoriz, MenuDestination.Converter),
            Triple("Contador", Icons.Default.AddCircle, MenuDestination.Counter)
        )

        LazyVerticalGrid(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(items) { (title, icon, destination) ->
                GridMenuCard(
                    title = title,
                    icon = icon,
                    onClick = { onNavigate(destination) }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MenuScreenGridPreview() {
    MenuScreenGrid()
}


