package es.rafapuig.pmdm.counter.data_store.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class CounterDataStore(
    private val counterDataStore: DataStore<Preferences>
) {

    private object Keys {
        val COUNTER = intPreferencesKey("counter")
    }

    val counterFlow: Flow<Int> =
        counterDataStore.data
            .map { preferences ->
                preferences[Keys.COUNTER] ?: 0
            }

    suspend fun increment() {
        counterDataStore.edit { preferences ->
            val current = preferences[Keys.COUNTER] ?: 0
            preferences[Keys.COUNTER] = current.inc()
        }
    }

    suspend fun decrement() {
        counterDataStore.edit { preferences ->
            val current = preferences[Keys.COUNTER] ?: 0
            preferences[Keys.COUNTER] = current.dec()
        }
    }

    suspend fun setCounter(value: Int) {
        /*counterDataStore.updateData {
            it.toMutablePreferences().apply {
                set(Keys.COUNTER, value)
            }
        }*/
        counterDataStore.edit { preferences ->
            preferences[Keys.COUNTER] = value
        }
    }

    suspend fun reset() {
        counterDataStore.edit { preferences ->
            preferences[Keys.COUNTER] = 0
        }
    }
}