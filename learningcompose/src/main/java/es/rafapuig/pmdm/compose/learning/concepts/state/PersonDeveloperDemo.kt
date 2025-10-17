package es.rafapuig.pmdm.compose.learning.concepts.state.developer

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

val LightGreen: Color = Color(149, 207, 149, 255)


/**
 * El modelo de datos de una persona en la UI
 */
data class Person(
    val name: String,
    val age: Int,
    val isDeveloper: Boolean = false
)

val persons = listOf(
    Person("Perico Palotes", 45),
    Person("Pedro Gado", 25, isDeveloper = true),
    Person("Aitor Tilla", 18, isDeveloper = true)
)

/**
 * Una clase parameter provider de objetos de tipo Person
 * para injectar un argumento de tipo persona en una funcion composable con anotacion @Preview
 * que declare un parametro de tipo Person
 */
class PersonProvider : PreviewParameterProvider<Person> {
    override val values: Sequence<Person>
        get() = persons.asSequence()
}


@Preview
@Composable
fun PersonItem(
    @PreviewParameter(PersonProvider::class) person: Person, //Parametro inyectable en una preview
    //onIsDeveloperChange: ((Boolean) -> Unit)? = null,
    onPersonChange: ((Person) -> Unit)? = null
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
            onCheckedChange = { onPersonChange?.invoke(person.copy(isDeveloper = it)) },
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

/**
 * Podemos hacer una previsualizacion si
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
        PersonItem(person) { person = it }

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
fun PersonList(persons: List<Person>, onPersonChange: (Person) -> Unit) {
    Column {
        persons.forEach { person ->
            PersonItem(person) { updatedPerson ->
                onPersonChange(updatedPerson)
            }
        }
    }
}


@Preview
@Composable
fun PersonListPreview() {
    /**
     * El estado es una lista de personas
     */
    var personList by remember {
        mutableStateOf(persons)
    }

    PersonList(personList) { updatedPerson ->
        personList = personList.map {
            if (it.name == updatedPerson.name) updatedPerson else it
        }
    }
}