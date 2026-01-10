package es.rafapuig.pmdm.clean.books.feature.book.data.remote.dto

import android.app.appsearch.SearchResults
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    @SerialName("docs") val results: List<SearchedBookDto>
)