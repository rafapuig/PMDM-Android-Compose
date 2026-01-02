package es.rafapuig.pmdm.di.hilt.counter.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.counterDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "counter_prefs"
)
