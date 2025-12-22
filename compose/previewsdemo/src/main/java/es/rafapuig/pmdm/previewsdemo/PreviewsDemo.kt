package es.rafapuig.pmdm.previewsdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Person(val name: String, val age: Int)

val persons = listOf(
    Person("Armando Bronca", 29),
    Person("Amador Denador", 31),
    Person("Sandra Mática", 25),
)

/**
 * PersonsProvider es un proveedor de parámetros para la vista de previsualización.
 * Por ello, implementa la interface PreviewParameterProvider<Person>.
 * implementando el getter de values que devuelve una secuencia de Person.
 */
class PersonsProvider : PreviewParameterProvider<Person> {
    override val values: Sequence<Person>
        get() = persons.asSequence()  //sequenceOf(*persons.toTypedArray()) //sequence { yieldAll(persons) }
}

/**
 * Función composable (composable, component)
 * función especial que se usa para crear interfaces de usuario (UI) mediante Compose
 * Cuando se llama a un composable
 * se le pasan datos (una persona en este caso)
 * y un conjunto de propiedades que definen su apariencia (tamaño de la fuente)
 * y comportamiento
 *
 * En esencia, un composable transforma datos en elementos de UI
 *
 * Una función composable no devuelve un valor,
 * emite elementos de UI al sistema de composición para su renderizado
 *
 * Una función composable puede llamar a otra función composable
 * para crear una jerarquía de composables
 */
@Composable // las funciones composables llevan la anotación @Composable
fun PersonItem(person: Person, fontSize: Int = 32) { // Recibe como dato una persona
    Column(modifier = Modifier.padding(16.dp)) { // Llamada a la función composable Column integrada (built-in)
        Text("Name: ${person.name}", fontSize = fontSize.sp)
        Text("Age: ${person.age}", fontSize = fontSize.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PersonItemPreview(
    @PreviewParameter(PersonsProvider::class)
    person: Person
) {
    /**
     * Llamamos a la función composable que queremos previsualizar
     * Se creará una preview por cada elemento proporcionado por el proveedor
     */
    PersonItem(person)
}


/**
 * Función composable PersonList
 *
 * Recibe como dato una lista de personas
 *
 * Como la función tiene parámetros de entrada
 * no se le puede aplicar la anotacion @Preview
 * Se usa una función wrapper que llama a esta función
 */
@Composable
fun PersonsListSimple(persons: List<Person>) {
    /**
     * Desde una función composable se puede llamar a otra función composable
     * personalizada (custom-built), en este caso a PersonItem
     */
    Column {
        persons.forEach { person ->
            PersonItem(person, 24)
        }
    }
}

@Composable
fun PersonsList(persons: List<Person>, showDivider: Boolean = true) {
    Column(modifier = Modifier.padding(16.dp)) {
        persons.forEachIndexed { index, person ->
            PersonItem(person, 24)
            if (showDivider && index < persons.lastIndex) HorizontalDivider()
        }
    }
}

@Composable
fun PersonsLazyList(persons: List<Person>, showDivider: Boolean = true) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        itemsIndexed(persons) { index, person ->
            PersonItem(person, 24)
            if (showDivider && index < persons.lastIndex) HorizontalDivider()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PersonsListSimplePreview() {
    PersonsListSimple(persons)
}

/**
 * Función wrapper que llama a PersonsList
 * normalmente la llamaremos igual que la función composable que queremos previsualizar
 * pero añadiéndole el sufijo Preview
 * La funcion llama a PersonsList con un argumento (lista de personas)
 * proporcionado en la llamada
 */
@Preview(showBackground = true)
@Composable
fun PersonsListPreview() {
    PersonsList(persons)
}

@Preview(showBackground = true)
@Composable
fun PersonsLazyListPreview() {
    PersonsLazyList(persons)
}


