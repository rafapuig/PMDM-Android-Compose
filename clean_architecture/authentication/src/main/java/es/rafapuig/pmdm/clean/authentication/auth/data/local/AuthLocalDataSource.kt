package es.rafapuig.pmdm.clean.authentication.auth.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import es.rafapuig.pmdm.clean.authentication.auth.data.datasource.AuthTokenDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Fuente de datos para autenticación local
 * Proporciona el token de autenticación
 *
 * Depende de un DataStore para persistir el token
 */

class AuthLocalDataSource(
    private val dataStore: DataStore<Preferences>
) : AuthTokenDataSource {

    private object AuthPreferencesKeys {
        val TOKEN = stringPreferencesKey("auth_token")
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[AuthPreferencesKeys.TOKEN] = token
        }
    }

    override fun getToken(): Flow<String?> {
        return dataStore.data.map { prefs ->
            prefs[AuthPreferencesKeys.TOKEN]
        }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}

