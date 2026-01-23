package es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.mappers

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.GenreEntity
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.Genre

fun GenreEntity.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun Genre.toEntity(): GenreEntity {
    return GenreEntity(
        id = id,
        name = name
    )
}

fun List<GenreEntity>.toDomain(): List<Genre> {
    return map { it.toDomain() }
}