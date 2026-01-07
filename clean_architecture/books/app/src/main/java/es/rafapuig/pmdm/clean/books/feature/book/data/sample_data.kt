package es.rafapuig.pmdm.clean.books.feature.book.data

import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book

val books = (1..20).map {
    Book(
        id = it.toString(),
        title = "Kotlin",
        authors = listOf("Rafael Puig"),
        averageRating = 4.55
    )
}