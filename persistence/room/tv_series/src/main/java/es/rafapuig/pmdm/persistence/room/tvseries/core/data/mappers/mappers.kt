package es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.dto.GenreDto
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.dto.TVSeriesDto
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.Genre
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.TVSeries

fun GenreDto.toDomain() =
    Genre(
        id = id,
        name = name
    )

fun List<GenreDto>.toDomain() =
    map { it.toDomain() }

