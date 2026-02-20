package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.feature.search.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter

@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // 🔍 Cuadro de búsqueda
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Buscar pelicula...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Clear, contentDescription = "Limpiar")
                }
            }
        },
        modifier = Modifier
            //.height(50.dp)
            .fillMaxWidth()
           .padding(bottom = 4.dp)
        ,
        singleLine = true,
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )

}


@Composable
fun SearchField2(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Usamos BasicTextField para tener control total
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .height(50.dp)
            //.padding(bottom = 4.dp)
            .fillMaxWidth()
            .clip(CircleShape) // Aplicamos la forma nosotros mismos
            .background(MaterialTheme.colorScheme.surfaceVariant), // Y el color de fondo
        singleLine = true,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onSurface, // Color del texto principal
            fontSize = MaterialTheme.typography.bodyLarge.fontSize // Usamos un tamaño de fuente estándar
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary), // Color del cursor
        decorationBox = { innerTextField ->
            // TextFieldDefaults.DecorationBox nos permite recrear la apariencia de un TextField
            // pero controlando el padding
            TextFieldDefaults.DecorationBox(
                value = query,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember{ MutableInteractionSource()},
                placeholder = { Text("Buscar película...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "Limpiar")
                        }
                    }
                },
                // --- ¡LA CLAVE ESTÁ AQUÍ! ---
                // Eliminamos completamente el padding vertical que causa el problema.
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                // Quitamos también los indicadores de línea
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
            )
        }
    )
}

