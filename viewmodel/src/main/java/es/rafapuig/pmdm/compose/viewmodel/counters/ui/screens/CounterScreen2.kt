@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.viewmodel.counters.ui.screens

import android.content.res.Configuration
import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.CounterStateFlowViewModel
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.CounterStateViewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

private val formatter = DecimalFormat("#,###")


@Composable
fun CounterScreen2(
    counter: Int,
    onIncrement: () -> Unit = {},
    onDecrement: () -> Unit = {},
    title: String = "Contador"
) {
    PMDMComposeTheme {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text(title) }) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement
                    .spacedBy(
                        32.dp,
                        Alignment.CenterVertically
                    )
            ) {
                Text(
                    "Contador",
                    style = MaterialTheme.typography.headlineMedium
                )


                Text(
                    "${formatter.format(counter)}",
                    style = MaterialTheme.typography.displayLarge
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    Button(onClick = { onDecrement() }) {
                        Text(" - ")
                    }
                    Button(onClick = { onIncrement() }) {
                        Text(" + ")
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CounterScreen2Preview_StateViewModel() {

    val viewModel = viewModel<CounterStateViewModel>()

    PMDMComposeTheme {
        CounterScreen2(
            title = "Contador con State ViewModel",
            counter = viewModel.counter,
            onIncrement = viewModel::increment,
            onDecrement = viewModel::decrement
        )
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CounterScreen2Preview_StateFlowViewModel() {

    val viewModel = viewModel<CounterStateFlowViewModel>()

    val counter by viewModel.counter.collectAsState()

    PMDMComposeTheme {
        CounterScreen2(
            title = "Contador con StateFlow ViewModel",
            counter = counter,
            onIncrement = viewModel::increment,
            onDecrement = viewModel::decrement
        )
    }
}