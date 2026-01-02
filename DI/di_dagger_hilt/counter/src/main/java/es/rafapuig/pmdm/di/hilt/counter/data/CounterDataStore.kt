package es.rafapuig.pmdm.di.hilt.counter.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** https://developer.android.com/training/dependency-injection/hilt-android#define-bindings */

class CounterDataStore @Inject constructor(
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