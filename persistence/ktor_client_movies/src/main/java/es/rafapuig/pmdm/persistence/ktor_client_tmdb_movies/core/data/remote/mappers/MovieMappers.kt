package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.mappers

import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.dto.MovieDto
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.model.Movie

/**
 * Mappers de Movie
 * (Un mapper trasforma un objeto de un tipo en otro,
 * en este caso de MovieDto definido en la capa de datos
 * a Movie definido en la capa de dominio)
 *
 * DTO -> Domain
 *
 * Función de extensión para convertir un MovieDto en un Movie
 */

fun MovieDto.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath ?: "/",
    voteAverage = voteAverage
)