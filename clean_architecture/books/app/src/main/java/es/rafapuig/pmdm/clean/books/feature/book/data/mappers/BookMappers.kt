package es.rafapuig.pmdm.clean.books.feature.book.data.mappers

import es.rafapuig.pmdm.clean.books.feature.book.data.remote.dto.SearchedBookDto
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book

fun SearchedBookDto.toBook() = Book(
    id = id,
    title = title,
    imageUrl = coverEditionKey?.let {
        "https://covers.openlibrary.org/b/olid/${it}-L.jpg"
    } ?: "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg",
    authors = authorNames ?: emptyList(),
    description = null,
    languages = languages ?: emptyList(),
    firstPublishYear.toString(),
    averageRating = ratingsAverage,
    ratingsCount = ratingsCount,
    numPages = numberOfPagesMedian,
    numEditions = numEditions ?: 0
)
