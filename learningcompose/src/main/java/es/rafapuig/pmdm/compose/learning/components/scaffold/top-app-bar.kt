package es.rafapuig.pmdm.compose.learning.components.scaffold

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachEmail
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.pastel.PMDMComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ScaffoldWithTopAppBarDemo() {
    /**
     * Comportamiento cuando se hace scroll en el contenido de un Scaffold
     * donde se use una TopAppBar que haya aplicado este comportamiento.
     */
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val myTopAppBar = @Composable {
        TopAppBar(
            // Este composable es experimental
            title = { Text(text = "Top App Bar") },
            navigationIcon = { // Icono a la izquierda del título
                Icon(Icons.Filled.Menu, null)
            },
            actions = {// Iconos a la derecha del título (RowScope)
                Icon(Icons.Filled.AttachEmail, null)
                Icon(Icons.Filled.AttachFile, null)
                Icon(Icons.Filled.Cast, null)
                Icon(Icons.Filled.MoreVert, null)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                scrolledContainerColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            scrollBehavior = scrollBehavior // aplicamos el comportamiendo cuando haya scroll en el contenido del Scaffold
        )
    }

    PMDMComposeTheme {
        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection), //para que la topAppBar detecte el scroll
            topBar = myTopAppBar,
        ) { innerPadding ->
            Content(innerPadding)
        }
    }
}

@Composable
fun Content(innerPadding: PaddingValues) {
    LazyColumn(
        contentPadding = innerPadding,
        modifier = Modifier
            //.padding(innerPadding)
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(listOf(Color.LightGray, Color.DarkGray))
            )
    ) {
        items(100) {
            Text("Item ${it + 1}")
        }
    }
}