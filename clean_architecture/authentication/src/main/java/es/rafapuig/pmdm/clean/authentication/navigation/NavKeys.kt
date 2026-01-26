package es.rafapuig.pmdm.clean.authentication.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Cada destino es un NavKey serializable que representa una pantalla
 */

@Serializable
object LoginKey : NavKey

@Serializable
object RegisterKey : NavKey

@Serializable
object MainKey : NavKey