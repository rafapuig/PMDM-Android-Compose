package es.rafapuig.pmdm.clean.books.feature.book.domain.model

data class Book(
    val id: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val authors: List<String> = emptyList(),
    val description: String? = null,
    val languages: List<String> = emptyList(),
    val firstPublishedYear: String? = null,
    val averageRating: Double? = null,
    val ratingsCount: Int? = null,
    val numPages: Int? = null,
    val numEditions: Int = 0
)
