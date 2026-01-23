package es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model

data class TVSeries(
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val backdropPath: String = "",
    val genres: List<Genre> = emptyList(),
    //val originCountry: List<String> = listOf(),
    val originalLanguage: String = "",
    val originalName: String = "",
    val popularity: Double = 0.0,
    val firstAirDate: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val adult: Boolean = false
)