package es.rafapuig.pmdm.compose.exercises.navigation.exercise1

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * https://developer.android.com/guide/navigation/navigation-3
 */

/**
 * Definimos las claves que identifican a cada pantalla de la app
 */
@Serializable
object HomeScreen : NavKey

@Serializable
data class ProfileScreen(val userId: Int) : NavKey

@Serializable
object SettingsScreen : NavKey
