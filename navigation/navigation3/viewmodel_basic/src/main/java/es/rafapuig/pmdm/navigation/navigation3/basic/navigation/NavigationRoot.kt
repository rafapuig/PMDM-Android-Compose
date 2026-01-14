package es.rafapuig.pmdm.navigation.navigation3.basic.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.detail.DetailScreenRoot
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.detail.DetailViewModel
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.home.HomeScreen
import es.rafapuig.pmdm.navigation.navigation3.basic.presentation.list.ItemListScreenRoot
import kotlinx.serialization.Serializable

/**
 * Rutas de navegación
 *
 * Para poder añadir estas rutas en una back stack persistente (saveable)
 * que sobreviva a los cambios de configuración del dispositivo
 * las marcamos con @Serializable
 * (Para ello añade el plugin org.jetbrains.kotlin.plugin.serialization
 *
 * Además deben implementar el interface NavKey para poder ser añadidos
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
 * por eso utilizamos un data class y no un data object
 */
@Serializable
data class Detail(val id: Int) : NavKey

/**
 * Sin embargo si utiliamos rutas de este modo cuando cambia la configuracion del dispositivo
 * se pierde el estado de la navegación y volvemos a la pantalla incial
 */

@Composable
fun NavigationRoot() {

    /**
     * Se utiliza una lista mutable para representar la pila (backStack) de navegación
     * La hemos iniciado con la ruta Home (pantalla inicial)
     * Para que la pila back stack sea persistence se usa la función rememberNavBackStack
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
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),

            /**
             * La función rememberViewModelStoreNavEntryDecorator
             * Controla el ciclo de vida del viewmodel,
             * lo asocia con la NavEntry donde se crea el viewmodel
             * Cuando se apila en la back stack la ruta asociada con la NavEntry
             * se crea el ViewModel y se destruye se elimina de la back stack
             * la ruta
             */
            rememberViewModelStoreNavEntryDecorator()
        ),

        /**
         *  El parámetro entryProvider
         *  Para configurarlo pordemos usar el entryProvider DSL
         *  para definir el contenido de cada ruta
         *  La función entry<RouteType> se usa para asociar una ruta con el contenido
         */

        entryProvider = entryProvider {

            entry<Home> {
                HomeScreen(
                    { backStack.add(List) }
                )
            }

            entry<List> {
                ItemListScreenRoot(
                    viewModel = viewModel(),
                    onNavigateToDetail = { id -> backStack.add(Detail(id)) }
                )
            }

            entry<Detail> {
                /**
                 * Necesitamos un nuevo ViewModel por cada instancia de ruta Detail
                 * (Detail es una data class, por tanto, se crea una instancia
                 * por cada valor de id que se pase)
                 *
                 * Para instanciar un ViewModel con parametros en el constructor
                 * necesitamos una factoria
                 * (no necesariamente,
                 * si usamos el API para ViewModels de Compose
                 * la funcion viewmodel cuando se le pasa una lambda
                 * con la instanciación de un ViewModel
                 * proporcionando los argumentos al constructor
                 * )
                 */
                DetailScreenRoot(
                    id = it.id,
                    viewModel = viewModel { DetailViewModel(it.id) }, //viewModel(factory = DetailViewModel.Factory(it.id)),
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }

    )


}