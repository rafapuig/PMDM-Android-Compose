package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<MovieDto> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)