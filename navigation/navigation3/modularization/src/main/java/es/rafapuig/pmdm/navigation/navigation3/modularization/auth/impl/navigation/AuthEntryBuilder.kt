package es.rafapuig.pmdm.navigation.navigation3.modularization.auth.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import es.rafapuig.pmdm.navigation.navigation3.modularization.auth.api.navigation.Login
import es.rafapuig.pmdm.navigation.navigation3.modularization.auth.impl.presentation.login.LoginScreen
import es.rafapuig.pmdm.navigation.navigation3.modularization.main.api.navigation.Home
import es.rafapuig.pmdm.navigation.navigation3.modularization.main.api.navigation.navigateToHome

fun EntryProviderScope<NavKey>.authEntryBuilder(
    backStack: NavBackStack<NavKey> // recibimos inyectada la backStack (objeto para modificar el estado de la navegación)
) {

    entry<Login> {
        LoginScreen(
            onLogin = { backStack.navigateToHome() }
        )
    }

}