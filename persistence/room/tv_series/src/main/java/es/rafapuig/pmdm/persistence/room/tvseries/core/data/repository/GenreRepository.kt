package es.rafapuig.pmdm.persistence.room.tvseries.core.data.repository

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetGenreDataSource
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao.GenreDao
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.mappers.toDomain
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.mappers.toEntity
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.Genre
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GenreRepositoryImpl(
    private val assetDataSource: AssetGenreDataSource,
    private val genreDao: GenreDao
): GenreRepository {

    override fun observeGenres(): Flow<List<Genre>> =
        genreDao.genres
            .map { it.map { entity -> entity.toDomain() } }

    override suspend fun preloadIfEmpty() {
        if (genreDao.count() == 0) {
            val genres = assetDataSource.loadGenres()
            genreDao.insertAll(genres.map { it.toEntity() })
        }
    }
}




