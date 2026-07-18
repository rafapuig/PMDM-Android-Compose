package es.rafapuig.pmdm.navigation.navigation3.modularization.main.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import es.rafapuig.pmdm.navigation.navigation3.modularization.main.api.navigation.Home
import es.rafapuig.pmdm.navigation.navigation3.modularization.main.impl.navigation.presentation.HomeScreen

fun EntryProviderScope<NavKey>.mainEntryBuilder(
    backStack: NavBackStack<NavKey>
) {

    entry<Home> {
        HomeScreen(
            onBack = { backStack.removeLastOrNull() }
        )
    }

}