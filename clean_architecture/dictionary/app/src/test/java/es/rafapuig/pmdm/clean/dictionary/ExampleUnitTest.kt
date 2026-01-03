package es.rafapuig.pmdm.clean.dictionary

import es.rafapuig.pmdm.clean.dictionary.core.data.local.Converters
import es.rafapuig.pmdm.clean.dictionary.core.data.local.MeaningList
import es.rafapuig.pmdm.clean.dictionary.core.data.util.KotlinSerializationJsonParser
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Definition
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Meaning
import kotlinx.serialization.json.Json
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testJson() {
        val meanings = listOf(
            Meaning(
                partOfSpeech = "noun",
                definitions = listOf(
                    Definition(
                        antonyms = listOf("hola", "adios"),
                        definition = "saludo",
                        example = "hola mundo",
                        synonyms = listOf("hola", "adios")
                    )
                ),
                synonyms = listOf("hola", "adios"),
                antonyms = listOf("hola", "adios")
            )
        )
    }
}