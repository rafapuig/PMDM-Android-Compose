package es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.dto.TVSeriesDto
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.TVSeriesEntity
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.TVSeriesWithGenres
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.mappers.toDomain
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.TVSeries


fun TVSeriesDto.toDomain() =
    TVSeries(
        id = id,
        name = name
    )

fun List<TVSeriesDto>.toDomain() =
    map { it.toDomain() }

fun TVSeriesDto.toEntity() =
    TVSeriesEntity(
        id = id,
        name = name,
        adult = adult,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        voteCount = voteCount,
        firstAirDate = firstAirDate,
        popularity = popularity,
        originalLanguage = originalLanguage,
        originalName = originalName
    )

fun List<TVSeriesDto>.toEntity() =
    map { it.toEntity() }


fun TVSeriesWithGenres.toDomain() =
    TVSeries(
        id = tvSeries.id,
        name = tvSeries.name,
        posterPath = tvSeries.posterPath,
        backdropPath = tvSeries.backdropPath,
        overview = tvSeries.overview,
        genres = genres.map { it.toDomain() }
    )
