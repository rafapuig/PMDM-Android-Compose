package es.rafapuig.pmdm.navigation.navigation3.modularization.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.navigation.navigation3.modularization.auth.api.navigation.Login

typealias EntryBuilder = EntryProviderScope<NavKey>.(NavBackStack<NavKey>) -> Unit

@Composable
fun NavigationRoot(
    entryBuilders : Set<EntryBuilder>
) {
    val backStack = rememberNavBackStack(Login)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {

            entryBuilders.forEach { entryBuilder ->
                // Inyectamos la back stack en cada entry builder para que las entradas
                // puedan navegar a otras entradas alterando el contenido de la backStack
                this.entryBuilder(backStack)
            }
        }
    )

}