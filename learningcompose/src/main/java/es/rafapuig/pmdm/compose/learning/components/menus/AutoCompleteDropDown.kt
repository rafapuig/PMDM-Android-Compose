package es.rafapuig.pmdm.compose.learning.components.menus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.data.Cities
import es.rafapuig.pmdm.compose.learning.data.City
import es.rafapuig.pmdm.compose.learning.data.SampleData
import es.rafapuig.pmdm.compose.learning.utils.fuzzyScored

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AutocompleteDropdown(
    value: String,
    onValueChange: (String) -> Unit,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    items: List<T>,
    label: String,
    itemLabel: (T) -> String,
) {
    var expanded by remember { mutableStateOf(false) }

    // Filtrar según el texto
    val filtered = remember(value) {
        if (value.isBlank()) items
        else items.fuzzyScored(value, { itemLabel(this) } )
        //items.filter { itemLabel(it).contains(value, ignoreCase = true) }
    }

    ExposedDropdownMenuBox(
        expanded = expanded && filtered.isNotEmpty(),
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                expanded = true
            },
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
            singleLine = true
        )

        ExposedDropdownMenu(
            expanded = expanded && filtered.isNotEmpty(),
            onDismissRequest = { expanded = false }
        ) {
            filtered.forEach { item ->
                DropdownMenuItem(
                    text = { Text(itemLabel(item)) },
                    onClick = {
                        onValueChange(itemLabel(item))
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}




@Preview
@Composable
fun AutocompleteDropdownExample() {

    var text by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf<City?>(null) }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            AutocompleteDropdown(
                value = text,
                onValueChange = { newText ->
                    text = newText
                    selectedCity = null // limpiar si escribe algo nuevo
                },
                selectedItem = selectedCity,
                onItemSelected = { city ->
                    selectedCity = city
                },
                items = SampleData.Cities.sortedBy { it.name },
                label = "Ciudad",
                itemLabel = { it.name }
            )

            Spacer(Modifier.height(16.dp))
            Text("Seleccionada: ${selectedCity?.name ?: "-"}")
        }
    }
}

