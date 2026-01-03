package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Double
)

