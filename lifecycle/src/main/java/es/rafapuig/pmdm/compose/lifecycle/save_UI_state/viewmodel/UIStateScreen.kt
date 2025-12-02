package es.rafapuig.pmdm.compose.lifecycle.save_UI_state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.UiStateViewModel

@Composable
fun UIStateScreenRoot(
    viewModel: UiStateViewModel,
    modifier: Modifier = Modifier
) {

    UIStateScreen(
        counterNormal = viewModel.counterNormal,
        counterSaved = viewModel.counterSaved,
        counterState = viewModel.counterState,
        counterSavedState = viewModel.counterSavedState,
        counterSavedStateBySaveable = viewModel.counterSavedStateBySaveable,
        counterFlow = viewModel.counterFlow.collectAsState().value,
        counterSavedMutableFlow = viewModel.counterSavedMutableFlow.collectAsState().value,
        counterSavedFlow = viewModel.counterSavedFlow.collectAsState().value,
        onClick = {
            with(viewModel) {
                onCounterNormalChange(counterNormal + 1)
                onCounterSavedChange(counterSaved + 1)
                onCounterStateChange(counterState + 1)
                onCounterSavedStateChange(counterSavedState + 1)
                onCounterSavedStateBySaveableChange(counterSavedStateBySaveable + 1)
                onCounterFlowChange(counterFlow.value + 1)
                onCounterSavedMutableFlowChange(counterSavedMutableFlow.value + 1)
                onCounterSavedFlowChange(counterSavedFlow.value + 1)
            }
        },
        modifier = modifier
    )
}


@Composable
fun UIStateScreen(
    counterNormal: Int,
    counterSaved: Int,
    counterState: Int,
    counterSavedState: Int,
    counterSavedStateBySaveable: Int,
    counterFlow: Int,
    counterSavedMutableFlow: Int,
    counterSavedFlow: Int,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {

            val style = MaterialTheme.typography.headlineSmall

            CompositionLocalProvider(LocalTextStyle provides style) {

                Text(text = "Normal counter: $counterNormal")
                Text(text = "Saved  counter: $counterSaved")
                Text(text = "State counter: $counterState")
                Text(text = "Saved State counter: $counterSavedState")
                Text(text = "Saved State by Saveable: $counterSavedStateBySaveable")
                Text(text = "Flow counter: $counterFlow")
                Text(text = "Saved Mutable Flow counter: $counterSavedMutableFlow")
                Text(text = "Saved Flow counter: $counterSavedFlow")

                Button(onClick = onClick) {
                    Text(text = "Incrementar")
                }
            }
        }
    }
}

@Preview
@Composable
fun UIStateScreenPreview() {
    UIStateScreen(
        counterNormal = 0,
        counterSaved = 0,
        counterState = 0,
        counterSavedState = 0,
        counterSavedStateBySaveable = 0,
        counterFlow = 0,
        counterSavedMutableFlow = 0,
        counterSavedFlow = 0
    )
}


