package es.rafapuig.pmdm.navigation.navigation3.basic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.detail.DetailScreen
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.home.HomeScreen
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.list.ItemListScreen
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.sampleItems

/**
 * Rutas de navegación
 */

/**
 * Ruta simple (sin parametros) que representa a la pantalla Home
 * (Como no necesita parametros se utiliza un data object)
 */

data object Home

/**
 * Ruta simple (sin parámetros) que representa a la pantalla Lista de items
 * (Como no necesita parámetros se utiliza un data object)
 */
data object List


/**
 * Ruta a la pantalla Detail de la app y que requiere un parametro id
 * por eso utilizamos un data class y no un data object
 */
data class Detail(val id: String)

/**
 * Sin embargo si utiliamos rutas de este modo cuando cambia la configuracion del dispositivo
 * se pierde el estado de la navegación y volvemos a la pantalla incial
 */

@Composable
fun NavigationRoot() {

    /**
     * Se utiliza una lista mutable para representar la pila (backStack) de navegación
     * La hemos iniciado con la ruta Home (pantalla inicial)
     */
    val backStack = remember { mutableStateListOf<Any>(Home) }

    /**
     * El composable NavDisplay se usa para mostrar la pantalla actual
     * (la que se encuentra en la parte superior de la pila, es decir, el último elemento
     * de la lista backStack)
     */
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },

        /**
         *  El parámetro entryProvider
         *  es una lambda que recibe una ruta desde la back stack (T) y devuelve un NavEntry<T>.
         *  (En este ejemplo, t es el tipo Any)
         *  Dentro, mediante un when se determina que pantalla mostrar en función de la ruta.
         */

        entryProvider = { route ->
            when (route) {
                is Home -> NavEntry(route) {
                    HomeScreen(
                        { backStack.add(List) }
                    )
                }

                is List -> NavEntry(route) {
                    ItemListScreen(
                        sampleItems,
                        { backStack.add(Detail(it)) }
                    )
                }

                is Detail -> NavEntry(route) {
                    DetailScreen(
                        id = route.id,
                        onBack = { backStack.removeLastOrNull() }
                    )
                }

                else -> error("Ruta no reconocida: $route")
            }
        }

    )


}