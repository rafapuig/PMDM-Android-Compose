package es.rafapuig.pmdm.clean.counter.app_mvi_one_off_events_as_channel_flow_with_data_store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.clean.counter.domain.RepositoryProvider
import es.rafapuig.pmdm.clean.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.clean.counter.presentation.ui.theme.CleanCounterTheme
import es.rafapuig.pmdm.counter.data_store.data.CounterDataStore
import es.rafapuig.pmdm.counter.data_store.data.CounterRepositoryImpl
import es.rafapuig.pmdm.counter.data_store.data.counterDataStore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RepositoryProvider.init(
            CounterRepositoryImpl(
                CounterDataStore(applicationContext.counterDataStore)
            )
        )

        enableEdgeToEdge()
        setContent {
            CleanCounterTheme {
                MVICounterScreenRoot()
            }
        }
    }
}

