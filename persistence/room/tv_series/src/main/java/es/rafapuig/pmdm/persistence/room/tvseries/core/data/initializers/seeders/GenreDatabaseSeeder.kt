package es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.seeders

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetGenreDataSource
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao.GenreDao
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.mappers.toEntity

class GenreDatabaseSeeder(
    private val assetDataSource: AssetGenreDataSource,
    private val genreDao: GenreDao
) {

    suspend fun seedIfEmpty() {
        if (genreDao.count() == 0) {
            val genres = assetDataSource.loadGenres()
            genreDao.insertAll(genres.map { it.toEntity() })
        }
    }
}
