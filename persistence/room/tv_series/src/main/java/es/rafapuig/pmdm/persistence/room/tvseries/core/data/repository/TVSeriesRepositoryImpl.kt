package es.rafapuig.pmdm.persistence.room.tvseries.core.data.repository

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.toDomain
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao.TVSeriesDao
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.repository.TVSeriesRepository
import kotlinx.coroutines.flow.map

class TVSeriesRepositoryImpl(
    private val tvSeriesDao: TVSeriesDao
) : TVSeriesRepository {
    override val tvSeriesWithGenres =
        tvSeriesDao.observeTvSeriesWithGenres().map {
            it.map { tvSeriesWithGenres ->
                tvSeriesWithGenres.toDomain()
            }
        }
}