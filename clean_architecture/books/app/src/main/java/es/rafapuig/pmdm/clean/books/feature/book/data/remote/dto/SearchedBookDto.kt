package es.rafapuig.pmdm.clean.books.feature.book.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedBookDto(
    @SerialName("key") val id: String,
    @SerialName("title") val title: String,
    @SerialName("language") val languages: List<String>? = null,
    @SerialName("cover_i") val coverAlternativeKey: Int? = null,
    @SerialName("author_key") val authorKeys: List<String>? = null,
    @SerialName("author_name") val authorNames: List<String>? = null,
    @SerialName("cover_edition_key") val coverEditionKey: String? = null,
    @SerialName("first_publish_year") val firstPublishYear: Int? = null,
    @SerialName("ratings_average") val ratingsAverage: Double? = null,
    @SerialName("ratings_count") val ratingsCount: Int? = null,
    @SerialName("number_of_pages_median") val numberOfPagesMedian: Int? = null,
    @SerialName("edition_count") val numEditions: Int? = null
)
