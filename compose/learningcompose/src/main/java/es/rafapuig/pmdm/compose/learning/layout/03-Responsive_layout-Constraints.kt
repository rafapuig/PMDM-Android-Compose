package es.rafapuig.pmdm.compose.learning.layout

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


/**
 * RESTRICCIONES DE TAMAÑO (CONSTRAINTS)
 *
 * https://developer.android.com/develop/ui/compose/layouts/constraints-modifiers
 * Constraints and modifier order - https://www.youtube.com/watch?v=OeC5jMV342A
 *
 * Advanced layout concepts - https://www.youtube.com/watch?v=l6rAoph5UgI
 *
 * Se puede usar BoxWithConstraints para acceder a las dimensiones
 * del layout padre.
 * Dichas measurement constraints se acceden mediante propiedades del
 * objeto BoxWithConstraintsScope
 */

/**
 *
 * https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/BoxWithConstraintsScope
 */

private fun BoxWithConstraintsScope.getConstraintsText(): String =
    "Mi minWidth: $minWidth y mi maxWidth: $maxWidth\n" +
            "MinHeight: $minHeight, maxHeight: $maxHeight\n" +
            "Tiene límites de anchura? ${constraints.hasBoundedWidth}\n" +
            "Tiene límites de altura? ${constraints.hasBoundedHeight}\n" +
            "Tiene una anchura fija? ${constraints.hasFixedWidth}\n" +
            "Tiene una altura fija? ${constraints.hasFixedHeight}\n"

@Preview(showBackground = true)
@Composable
fun WithConstraintsComposable1() {

    BoxWithConstraints {
        Text(getConstraintsText())
    }
}

@Preview(showBackground = true, widthDp = 380)
@Composable
fun WithConstraintsComposable2() {

    BoxWithConstraints {
        Text(getConstraintsText())
    }
}

@Preview(showBackground = true, widthDp = 200)
@Composable
fun WithConstraintsComposable3() {

    BoxWithConstraints {
        Text(getConstraintsText())
    }
}

@Preview(showBackground = true, heightDp = 150)
@Composable
fun WithConstraintsComposable4() {

    BoxWithConstraints {
        Text(getConstraintsText())
    }
}

@Preview(showBackground = true)
@Composable
fun WithConstraintsComposable5() {

    BoxWithConstraints(
        /** hacemos que el padre no imponga límites en las dimensiones **/
        modifier = Modifier.wrapContentSize(unbounded = true)
    ) {
        Text(getConstraintsText())
    }
}

@Preview(showBackground = true)
@Composable
fun WithConstraintsComposable6() {

    BoxWithConstraints(
        /** hacemos que el padre no imponga límites en la anchura **/
        modifier = Modifier.wrapContentWidth(unbounded = true)
    ) {
        Text(getConstraintsText())
    }
}

@Preview(showBackground = true)
@Composable
fun WithConstraintsComposable7() {

    BoxWithConstraints(
        /** hacemos que el padre no imponga límites en la anchura **/
        modifier = Modifier.wrapContentHeight(unbounded = true)
    ) {
        Text(getConstraintsText())
    }
}