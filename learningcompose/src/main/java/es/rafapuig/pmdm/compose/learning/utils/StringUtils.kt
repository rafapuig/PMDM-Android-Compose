package es.rafapuig.pmdm.compose.learning.utils

import java.text.Normalizer

fun String.normalize(): String =
    Normalizer.normalize(this, Normalizer.Form.NFD)
        .replace("\\p{M}".toRegex(), "") // elimina marcas diacríticas (acentos, ñ, ...)
        .lowercase()


fun fuzzyScore(text: String, query: String): Int {
    val t = text.normalize()
    val q = query.normalize()

    if (q.isEmpty()) return 0

    var score = 0
    var tIndex = 0

    for (qChar in q) {
        val foundIndex = t.indexOf(qChar, startIndex = tIndex)
        if (foundIndex == -1) return 0
        if (foundIndex == tIndex) score += 2 else score += 1
        tIndex = foundIndex + 1
    }

    return score
}

fun <T> Iterable<T>.fuzzyScored(query: String, toText: T.() -> String = { this.toString() }): List<T> =
    map { item -> item to fuzzyScore(item.toText(), query) }
        .filter { (_, score) -> score > 0 }
        .sortedByDescending { (_, score) -> score }
        .map { (item, _) -> item }