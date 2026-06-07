package es.rafapuig.pmdm.compose.learning.concepts.state

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme

private data class Person(val name: String, val age: Int)

private val persons = listOf(
    Person("Belen Tilla", 35),
    Person("Victor Nado", 30),
    Person("Amador Denador", 45)
)

private enum class PersonSortOrder { None, ByName, ByAge }


/**
 * Función composable con estado (STATEFUL COMPOSABLE)
 *
 * Una función composable puede almacenar estado
 * para determinar como se debe comportar cuando es llamada
 * Se usa la función remember para mantener el estado (recordarlo entre recomposiciones)
 * y la función factoría mutableStateOf para crear el estado inicial
 */
@Preview(showBackground = true)
@Composable
fun PersonListScreen() {

    var sortOrder by remember {
        mutableStateOf(PersonSortOrder.None)
    }

    Column {
        Row {
            Text(
                text = "Name",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { sortOrder = PersonSortOrder.ByName }
                    .weight(1f)
            )
            Text(
                text = "Age",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { sortOrder = PersonSortOrder.ByAge }
            )
        }
        HorizontalDivider(thickness = 2.dp)
        PersonList(persons, sortOrder)
    }
}

/**
 * Función sin estado STATELESS
 * Aunque se le pase y haga uso del sortOrder, que es valor del estado
 * almacenado en PersonListScreen, esta función no almacena ningún estado
 * por ella misma.
 */
@Composable
private fun PersonList(
    persons: List<Person>,
    sortOrder: PersonSortOrder
) {
    val sortedPersons = when (sortOrder) {
        PersonSortOrder.None -> persons
        PersonSortOrder.ByName -> persons.sortedBy { it.name }
        PersonSortOrder.ByAge -> persons.sortedBy { it.age }
    }

    Column {
        sortedPersons.forEach { person ->
            PersonListItem(person)
        }
    }
}


/**
 * Función sin estado STATELESS
 * Su comportamiento únicamente depende del valor de los parámetros
 */
@Composable
private fun PersonListItem(
    person: Person,
    fontSize: Int = 32
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = person.name, fontSize = fontSize.sp, modifier = Modifier.weight(1f))
        Text(text = person.age.toString(), fontSize = fontSize.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun PersonListScreenPreview() {
    PMDMComposeTheme(darkTheme = true) {
        Surface {
            PersonListScreen()
        }
    }
}


/**
 * Veamos como podemos evitar duplicar el código para crear cada etiqueta de columna
 */
@Composable
fun RowScope.ColumnLabel(
    text: String,
    fontSize: Int = 32,
    expand: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .clickable { onClick?.invoke() }
                //.apply { if (expand) weight(1f) } // No funciona, ¿pq? Inmutabilidad
                //.let { if (expand) it.weight(1f) else it } // Si funciona pq devuelve resultado lambda
                then if (expand) Modifier.weight(1f) else Modifier

    )
}

@Preview(showBackground = true)
@Composable
fun PersonListScreenWithColumnLabels() {

    var sortOrder by remember {
        mutableStateOf(PersonSortOrder.None)
    }

    Column {
        Row {
            ColumnLabel(
                text = "Name",
                expand = true,
                onClick = { sortOrder = PersonSortOrder.ByName }
            )
            ColumnLabel(text = "Age") { sortOrder = PersonSortOrder.ByAge }
        }
        HorizontalDivider()
        PersonList(persons, sortOrder)
    }
}