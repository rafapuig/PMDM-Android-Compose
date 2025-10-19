package es.rafapuig.pmdm.compose.learning.concepts

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.tooling.preview.Preview

/**
 * COMPOSITION LOCAL
 *
 * Ya sabemos que una interface de usuario (UI) en Compose se ensambla
 * a partir de crear jerarquias de funciones composables
 * (llamadas de un composable padre a composables hijas)
 *
 * Tambien sabemos que Compose esta dirigido por estados (State-driven)
 * y que cambios en los estados generan recomposición de la UI
 * (lo que lleva a volver a llamar a funciones composables que hacen uso del valor del estado
 * que haya cambiado)
 *
 * Y además sabemos que un estado debe ser declarado en el nodo más alto necesario del arbol
 * de nodos de funciones composables
 * (definido por las llamadas a las funciones composables durante la composición)
 * Al hecho de subir la declaracion del estado a un nodo superior (funcion llamadora)
 * le denominamos STATE HOISTING
 *
 * Esto conduce a tener que pasar hacia abajo como argumento de llamada a las funciones composables
 * hijas el valor de estado.
 *
 * Tener que hacer esto, puede llegar a volverse inmanejable en algunas situaciones.
 * (Por ejemplo, cuando tenemos que pasar el estado multiples niveles hacia abajo en la jerarquía,
 * el padre pasa el valor a la función composable hija, y esta a otra hija y esta a otra ...)
 *
 * Al final, las funciones intemedias entre la que declara el estado y la que finalmente hace uso de él
 * deben declaran un parñametro adicional con la única finalidad de actuar como meras intermediarias,
 * es decir, recibir un argumento para ese parámetro extra declarado
 * y usarlo como argumento para llamar a las funciones composables descendentes directas intermedias
 * que a su vez harán lo propio hasta que el valor llegue
 * a la composable que hace verdadero uso del valor del estado.
 *
 * La solución es usar CompositionLocal
 */

/**
 * CompositionLocal proporciona una forma de hacer que
 * un estado declarado en un composable * en un lugar más arriba en la jerarquía del arbol
 * esté disponible a las funciones que ubicadas más abajo del arbol
 * sin tener que estar pasándolo a través de cada composable intermedio entre
 * el punto donde el estado es declarado y la función composable donde se usa.
 */

val DarkRedish = Color(0xFFa08d87)
val LightRedish = Color(0xFFffdbcf)

/**
 * Cualquier Composable tendrá acceso al valor del estado
 * LocalColor mediante la propiedad current del objeto ProvidableCompositionLocal
 * En este caso: var color = LocalColor.current
 */
val LocalColor = staticCompositionLocalOf { LightRedish }

@Composable
fun ComposableLivingThing() {

    val color = if (isSystemInDarkTheme()) DarkRedish else LightRedish

    Column {

        /**
         * Al llamar al composable hijo ComposablePlant no
         * cambiamos el valor del estado LocalColor
         * por tanto, en la propiedad current obtienen el valor inicial del estado
         * LocalColor
         */
        ComposablePlant() //2

        /**
         * Asignamos un valor al estado gestionado por el ProvidableCompositionLocal
         * la llamada a la función hija inmediata descendente se llama en la
         * lambda usada como último argumento de CompositionLocalProvider
         */
        CompositionLocalProvider(LocalColor provides color) {
            /**
             * En el composable ComposeAnimal y sus descendentes del valor del estado
             * LocalColor será el nuevo valor proporcionado para llamar a la función
             * ComposableAnimal (hasta que otro cambio en el estado ocurra en alguna
             * de las descendentes)
             */
            ComposableAnimal() //3
        }
    }
}

@Composable
fun ComposablePlant() {
    ComposableFlower() //4
}

@Composable
fun ComposableAnimal() {
    Text(
        "Composable Living Thing -> Animal",
        Modifier.background(LocalColor.current)
    )
    CompositionLocalProvider(LocalColor provides Color.Red) {
        ComposableMammal() //5
    }
}


@Composable
fun ComposableFlower() {
    ComposableRose() //6
}

@Composable
fun ComposableMammal() {
    Text(
        "Composable Living Thing -> Animal -> Mammal",
        Modifier.background(LocalColor.current)
    )
    CompositionLocalProvider(LocalColor provides Color.Green) {
        ComposableCat() //7
    }
    CompositionLocalProvider(LocalColor provides Color.Yellow) {
        ComposableDog() //8
    }
}

@Composable
fun ComposableRose() {
    /**
     * Como en las plantas no modificamos el valor inicial del estado LocalColor
     * El valor current para este composable será el valor inicial
     */
    val backgroundColor = LocalColor.current

    Text("Composable Living Thing -> Plant -> Flower -> Rose",
        Modifier.background(backgroundColor))
}


@Composable
fun ComposableCat() {
    Text(
        "Composable Living Thing -> Animal -> Mammal -> CAT",
        Modifier.background(LocalColor.current)
    )
}

@Composable
fun ComposableDog() {
    /**
     * Este composable tiene acceso al estado LocalColor (CompositionLocal)
     * al ser desdendente en la jerarquía del composable ComposableAnimal
     */
    val backgroundColor = LocalColor.current
    Text(
        "Composable Living Thing -> Animal -> Mammal -> Dog",
        Modifier.background(backgroundColor)
    )
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkView() {
    ComposableLivingThing()
}