package es.rafapuig.pmdm.navigation.navigation3.modularization.main.api.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import es.rafapuig.pmdm.navigation.navigation3.modularization.auth.api.navigation.Login
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey

fun NavBackStack<NavKey>.navigateToHome() {
    add(Home)
}