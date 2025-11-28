package es.rafapuig.pmdm.compose.viewmodel.counters.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CounterRepository(private val context: Context) {

    private object Keys {
        val COUNTER = intPreferencesKey("counter")
    }

    val counterFlow: Flow<Int> =
        context.counterDataStore.data
            .map { prefs ->
                prefs[Keys.COUNTER] ?: 0
            }

    suspend fun increment() {
        context.counterDataStore.edit { prefs ->
            val current = prefs[Keys.COUNTER] ?: 0
            prefs[Keys.COUNTER] = current + 1
        }
    }

    suspend fun decrement() {
        context.counterDataStore.edit { prefs ->
            val current = prefs[Keys.COUNTER] ?: 0
            prefs[Keys.COUNTER] = current - 1
        }
    }

    suspend fun setCounter(value: Int) {
        context.counterDataStore.edit { prefs ->
            prefs[Keys.COUNTER] = value
        }
    }

    suspend fun reset() {
        context.counterDataStore.edit { prefs ->
            prefs[Keys.COUNTER] = 0
        }
    }
}
