package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ColorSelectorRow(
    selectedIndex: Int = 0,
    onColorSelected: (Int) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        buttonColors.forEachIndexed { index, color ->
            ColorButton(
                color = color,
                isSelected = selectedIndex == index,
                onClick = { onColorSelected(index) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorSelectorRowPreview() {
    ColorSelectorRow()
}