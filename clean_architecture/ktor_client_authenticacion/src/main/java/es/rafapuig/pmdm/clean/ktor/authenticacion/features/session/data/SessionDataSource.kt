package es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private val Context.dataStore by preferencesDataStore("session_prefs")

interface SessionDataSource {
    fun getToken(): String?
    fun saveToken(token: String)
    fun clearSession()
}

class SessionDataStoreDataSource(context: Context) : SessionDataSource {

    private val dataStore = context.dataStore
    private val TOKEN_KEY = stringPreferencesKey("auth_token")

    override fun getToken(): String? =
        runBlocking { dataStore.data.first()[TOKEN_KEY] }


    override fun saveToken(token: String) {
        runBlocking {
            dataStore.edit { it[TOKEN_KEY] = token }
        }
    }


    override fun clearSession() {
        runBlocking { dataStore.edit { it.clear() } }
    }
}
