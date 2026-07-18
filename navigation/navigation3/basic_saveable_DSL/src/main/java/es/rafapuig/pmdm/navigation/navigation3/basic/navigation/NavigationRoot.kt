package es.rafapuig.pmdm.navigation.navigation3.basic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.detail.DetailScreen
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.home.HomeScreen
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.list.ItemListScreen
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.sampleItems
import kotlinx.serialization.Serializable

/**
 * Rutas de navegación
 *
 * Para poder añadir estas rutas en una back stack persistente (saveable)
 * que sobreviva a los cambios de configuración del dispositivo
 * las marcamos con @Serializable
 * (Para ello añade el plugin org.jetbrains.kotlin.plugin.serialization
 *
 * Además, deben implementar el interface NavKey para poder ser añadidos
 * a la pila de navegación peristente que se crea mediante la función rememberNavBackStack
 */

/**
 * Ruta simple (sin parámetros) que representa a la pantalla Home
 * (Como no necesita parámetros se utiliza un data object)
 */

@Serializable
data object Home : NavKey

/**
 * Ruta simple (sin parámetros) que representa a la pantalla Lista de items
 * (Como no necesita parámetros se utiliza un data object)
 */

@Serializable
data object List : NavKey


/**
 * Ruta a la pantalla Detail de la app y que requiere un parametro id
 * por eso utilizamos una data class y no un data object
 */
@Serializable
data class Detail(val id: String) : NavKey

/**
 * Si utilizamos rutas de este modo cuando cambia la configuración del dispositivo
 * No se pierde el estado de la navegación y NO volvemos a la pantalla incial
 */

@Composable
fun NavigationRoot() {

    /**
     * Se utiliza una lista mutable para representar la pila (backStack) de navegación
     * La hemos iniciado con la ruta Home (pantalla inicial)
     * Para que la pila back stack sea persistente se usa la función rememberNavBackStack
     * que crea una lista de elementos de tipo NavKey (por eso las rutas deben implementarlo)
     */
    val backStack = rememberNavBackStack(Home)

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
         *  Para configurarlo podemos usar el entryProvider DSL
         *  para definir el contenido de cada ruta
         *  La función entry<RouteType> se usa para asociar una ruta con el contenido
         */

        entryProvider = entryProvider {

            entry<Home> {
                HomeScreen(
                    onNavigateToList = { backStack.add(List) }
                )
            }

            entry<List> {
                ItemListScreen(
                    items = sampleItems,
                    onItemClick = { id -> backStack.add(Detail(id)) }
                )
            }

            entry<Detail> {
                DetailScreen(
                    id = it.id,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }

    )


}