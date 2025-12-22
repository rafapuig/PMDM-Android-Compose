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
import es.rafapuig.pmdm.compose.learning.data.SampleData
import es.rafapuig.pmdm.compose.learning.utils.fuzzyScore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> EditableDropDown(
    value: String,
    items: List<T>,
    modifier: Modifier = Modifier,
    label: String = "Selecciona una opción",
    itemLabel: (T) -> String = { it.toString() },
    onValueChange: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    val filteredItems = remember(value) {
        if (value.isBlank()) emptyList()
        else items
            .map { item -> item to fuzzyScore(itemLabel(item), value) }
            .filter { (_, score) -> score > 0 }
            .sortedByDescending { (_, score) -> score }
            .map { (item, _) -> item }

        /*items.filter { item ->
        itemLabel(item)
            .normalize()
            .contains(value.normalize(), ignoreCase = true)
    }*/
    }


    ExposedDropdownMenuBox(
        expanded = expanded && filteredItems.isNotEmpty(),
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = false,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                    enabled = true
                ) // 👈 Importante para posicionar el menú
                .fillMaxWidth(),
            singleLine = true
        )

        ExposedDropdownMenu(
            expanded = expanded && filteredItems.isNotEmpty(),
            onDismissRequest = { expanded = false }
        ) {
            filteredItems.forEach { item ->
                val label = itemLabel(item)
                DropdownMenuItem(
                    text = { Text(label) },
                    onClick = {
                        onValueChange(label)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun EditableDropDownPreview() {

    var city by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            EditableDropDown(
                value = city,
                items = SampleData.Cities,
                itemLabel = { it.name },
                onValueChange = { city = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Text(text = city)
        }
    }
}

