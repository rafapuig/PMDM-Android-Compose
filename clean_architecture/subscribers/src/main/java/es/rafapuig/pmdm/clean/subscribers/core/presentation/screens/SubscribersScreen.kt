package es.rafapuig.pmdm.clean.subscribers.core.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat

private val formatter = DecimalFormat("#,###")


@Composable
fun SubscribersScreen(subscribers: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                32.dp,
                Alignment.CenterVertically
            )
    ) {
        Text(
            "Subscriptores",
            style = MaterialTheme.typography.headlineMedium
        )


        Text(
            "${formatter.format(subscribers)}",
            style = MaterialTheme.typography.displayLarge
        )
    }

}

