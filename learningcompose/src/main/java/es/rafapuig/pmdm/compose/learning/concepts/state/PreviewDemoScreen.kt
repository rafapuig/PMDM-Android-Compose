package es.rafapuig.pmdm.compose.learning.concepts.state

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random


val LightGreen: Color = Color(149, 207, 149, 255)

data class Tag(
    val name: String,
    val color: Color
)

val tags = listOf(
    Tag("Accion", Color.Red),
    Tag("Aventura", Color.Blue),
    Tag("Drama", Color.Green),
    Tag("Comedia", Color.Yellow),
    Tag("Terror", Color.Magenta),
)

@Preview
@Composable
fun PreviewDemoScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MyButton("Hola Compose", 300.dp)
        MyButton("Adios Compose", color = Color.Magenta)
        TagList(tags)
    }
}

@Composable
private fun MyButton(text: String, width: Dp = 200.dp, color: Color = Color.Yellow) {
    Text(
        text,
        Modifier
            .clip(RoundedCornerShape(30))
            .background(color)
            .padding(16.dp)
            .width(width)
            .clickable {},
        textAlign = TextAlign.Center,
        color = Color.Blue
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagList(tags: List<Tag>) {
    FlowRow {
        tags.forEach { tag ->
            MyButton(
                text = tag.name,
                color = tag.color
            )
        }
    }
}


data class Person(
    val name: String,
    val age: Int,
    val isDeveloper: Boolean = true
)

val persons = listOf(
    Person("Perico Palotes", 45, isDeveloper = false),
    Person("Pedro Gado", 25, isDeveloper = true),
    Person("Aitor Tilla", 18, isDeveloper = true)
)

class PersonProvider : PreviewParameterProvider<Person> {
    override val values: Sequence<Person>
        get() = persons.asSequence()
}


@Preview
@Composable
fun PersonItem(
    @PreviewParameter(PersonProvider::class)
    person: Person, onIsDeveloperChange: (Boolean) -> Unit = {}
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
            onCheckedChange = onIsDeveloperChange,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Preview
@Composable
fun PersonItemPreview() {
    PersonItem(
        Person("Perico Palotes", 45, isDeveloper = false)
    )
}

fun Color.Companion.random() =
    with(Random) {
        Color(
            red = nextFloat(),
            green = nextFloat(),
            blue = nextFloat(),
            1f
        )
    }


fun testGenerateRandomColor() {
    val color = Color.random()

}

@Preview
@Composable
fun ColorBoxDemo() {

    var color by remember { mutableStateOf(Color.White) }

    Column {
        ColorBox(onColorChange = { color = it })
        ColorBox(color) {}
    }

}

@Composable
fun ColorBox(color: Color = Color.White, onColorChange: (Color) -> Unit = {}) {
    Box(
        Modifier
            .size(200.dp)
            .background(color)
            .clickable { onColorChange(Color.random()) }
    )
}


@Composable
fun SwitchDemo() {
    var checked by remember { mutableStateOf(false) }

    Switch(
        checked = checked,
        onCheckedChange = { checked = it }
    )
}

@Composable
fun IsDeveloperCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    //var checked by remember { mutableStateOf(false) }

    Checkbox(
        checked = checked,
        onCheckedChange = { onCheckedChange(it) }
    )
}

@Preview
@Composable
fun PersonDemo() {
    var person by remember {
        mutableStateOf(Person("Perico Palotes", 35, false))
    }

    Column {
        PersonItem(person) {
            person = person.copy(isDeveloper = it)
        }

        IsDeveloperCheckBox(
            checked = person.isDeveloper,
            onCheckedChange = { person = person.copy(isDeveloper = it) }
        )
    }
}

@Composable
fun PersonList(persons: List<Person>, onPersonChange: (Person) -> Unit) {
    Column {
        persons.forEach { person ->
            PersonItem(person) { isDeveloper ->
                onPersonChange(person.copy(isDeveloper = isDeveloper))
            }
        }
    }
}

@Preview
@Composable
fun PersonListPreview() {
    var personList by remember {
        mutableStateOf(persons)
    }
    PersonList(personList) { updatedPerson ->
        personList = personList.map {
            if (it.name == updatedPerson.name) updatedPerson else it
        }
    }
}
