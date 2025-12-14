package es.rafapuig.pmdm.compose.viewmodel.lifecycle.counter.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.counter.ui.CounterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterScreenRoot(viewModel: CounterViewModel = viewModel()) {

    val activity = LocalContext.current as ComponentActivity

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atras"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->

        val counter by viewModel.counter.collectAsStateWithLifecycle()

        CounterScreen(
            modifier = Modifier.padding(innerPadding),
            counter = counter,
            onIncrement = viewModel::onIncrement,
            onDecrement = viewModel::onDecrement,
            onReset = viewModel::onReset
        )
    }
}