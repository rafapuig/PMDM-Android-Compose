package es.rafapuig.pmdm.navigation.navigation3.modularization.auth.api.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Login : NavKey

fun NavBackStack<NavKey>.navigateToLogin() {
    add(Login)
}