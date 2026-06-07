package es.rafapuig.pmdm.compose.learning.concepts.state.developer2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LightGreen: Color =
    Color(149, 207, 149, 255)


/**
 * El modelo de datos de una persona en la UI
 */
data class Person(
    val name: String,
    val age: Int,
    val isDeveloper: Boolean = false
)

val persons = listOf(
    Person("Armando Bronca", 45),
    Person("Pedro Gado", 25, isDeveloper = true),
    Person("Aitor Tilla", 18, isDeveloper = true)
)


@Composable
fun PersonItem(
    person: Person, //Parámetro inyectable en una preview
    onIsDeveloperChange: ((Boolean) -> Unit)? = null,
    //onPersonChange: ((Person) -> Unit)? = null
) {
    Box(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30))
            .background(Color.LightGray)
                then (if (person.age > 18) Modifier.background(LightGreen) else Modifier)
            .padding(16.dp)


    ) {
        Column {
            Text(
                person.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.padding(2.dp))
            Text("${person.age} años", fontSize = 16.sp)
        }
        Switch(
            person.isDeveloper,
            onCheckedChange = {
                onIsDeveloperChange?.invoke(it)
                //onPersonChange?.invoke(person.copy(isDeveloper = it))
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

/**
 * Podemos hacer una previsualización si
 * creamos una función que llama al composable que queremos
 * previsualizar pasando un argumento de tipo persona
 * al parámetro del composable
 */
@Preview
@Composable
fun PersonItemPreview() {
    PersonItem(
        Person("Perico Palotes", 45, isDeveloper = false)
    )
}

/**
 * Una clase parameter provider de objetos de tipo Person
 * para inyectar un argumento de tipo persona
 * en una función composable con anotación @Preview
 * que declare un parámetro de tipo Person
 */
class PersonProvider : PreviewParameterProvider<Person> {
    override val values: Sequence<Person>
        get() = persons.asSequence()
}

@Preview
@Composable
fun PersonItemPreview(
    @PreviewParameter(PersonProvider::class)
    person: Person, //Parámetro inyectable en la preview
) {
    PersonItem(person)
}

@Composable
fun IsDeveloperCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Checkbox(
        checked = checked,
        onCheckedChange = { onCheckedChange(it) }
    )
}

@Preview
@Composable
fun PersonDemo() {
    /**
     * El estado es una persona
     */
    var person by remember {
        mutableStateOf(Person("Perico Palotes", 35, false))
    }

    Column {
        PersonItem(person) { isDeveloper ->
            person = person.copy(isDeveloper = isDeveloper)
        }

        IsDeveloperCheckBox(
            checked = person.isDeveloper,
            /**
             * Cambiamos la persona por otra igual
             * pero con la propiedad isDeveloper actualizada con el valor recibido en it
             */
            onCheckedChange = { person = person.copy(isDeveloper = it) }
        )
    }
}


@Composable
fun PersonList(
    persons: List<Person>,
    onPersonListChange: (Person) -> Unit
) {
    Column {
        persons.forEach { person ->
            PersonItem(
                person = person,
                onIsDeveloperChange = {
                    onPersonListChange(person.copy(isDeveloper = it))
                }
            )
        }
    }
}


@Preview
@Composable
fun PersonListPreview() {
    /**
     * El estado es una lista de personas
     */
    val personList = remember {
        mutableStateListOf(*persons.toTypedArray())
    }

    PersonList(personList) { updatedPerson ->
        // Buscamos la posición de la persona en la lista
        val index = personList
            .indexOfFirst { it.name == updatedPerson.name }

        // Si la encontramos la actualizamos
        if (index != -1) {
            personList[index] = updatedPerson
        }
    }
}