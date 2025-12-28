package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote

import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.dto.MovieDto
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.model.Movie

fun MovieDto.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath ?: "/",
    voteAverage = voteAverage
)