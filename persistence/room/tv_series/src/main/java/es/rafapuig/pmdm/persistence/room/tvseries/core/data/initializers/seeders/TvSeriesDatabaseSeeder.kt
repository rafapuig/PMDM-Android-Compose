package es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.seeders

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetTvSeriesDataSource
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.toEntity
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao.TVSeriesDao
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.TVSeriesGenreCrossRef

class TvSeriesDatabaseSeeder(
    private val assetDataSource: AssetTvSeriesDataSource,
    private val tvSeriesDao: TVSeriesDao
) {
    suspend fun seedIfEmpty() {

        val seriesDtos = assetDataSource.load()

        val seriesEntities = seriesDtos.toEntity()

        val crossRefs = seriesDtos.flatMap { it ->
            it.genreIds.map { genreId ->
                TVSeriesGenreCrossRef(
                    tvSeriesId = it.id,
                    genreId = genreId
                )
            }
        }

        tvSeriesDao.insertAll(seriesEntities)
        tvSeriesDao.insertCrossRefs(crossRefs)
    }
}