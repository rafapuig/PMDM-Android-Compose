package es.rafapuig.pmdm.clean.dictionary.feature.dictionary.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Definition
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.Meaning
import es.rafapuig.pmdm.clean.dictionary.core.domain.model.WordInfo
import kotlin.collections.listOf

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = wordInfo.word,
            style = MaterialTheme.typography.titleLarge
        )

        println(wordInfo.phonetics)

        Text(
            text = wordInfo.phonetics
                .joinToString { it.text ?: "" },
            fontWeight = FontWeight.Light
        )

        wordInfo.meanings.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)

            meaning.definitions.forEachIndexed { index, definition ->
                Text(text = "${index + 1}. ${definition.definition}")
                definition.example?.let { example ->
                    Text(text = "Example: $example")
                }
            }
            /*if (wordInfo.sourceUrls.isNotEmpty()) {
                Text(text = "Source URLs:")
                wordInfo.sourceUrls
                    .forEach { sourceUrl ->
                        Text(text = sourceUrl)
                    }
            }*/
            Spacer(modifier = Modifier.height(8.dp))
        }

    }

}

@Preview(showBackground = true)
@Composable
fun WordInfoItemPreview() {
    WordInfoItem(
        wordInfo = WordInfo(
            word = "hello",
            phonetics = listOf(),
            meanings = listOf(
                Meaning(
                    partOfSpeech = "greeting",
                    definitions = listOf(
                        Definition(
                            definition = "used as a greeting or to begin a phone conversation.",
                            example = "Hello, my friend!",
                            synonyms = listOf(),
                            antonyms = listOf()
                        )
                    )
                )
            ),
            sourceUrls = listOf()
        )
    )
}