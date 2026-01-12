package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.mappers

import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.dto.MovieDto
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.model.Movie

fun MovieDto.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath ?: "/",
    voteAverage = voteAverage
)