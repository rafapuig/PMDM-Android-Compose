package es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model

data class TopRatedResult(
    val page: Int = 0,
    val tvSeries: List<TVSeries> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)