package es.rafapuig.pmdm.persistence.room.tvseries.core.domain.repository

import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.TVSeries
import kotlinx.coroutines.flow.Flow

interface TVSeriesRepository {

    val tvSeriesWithGenres : Flow<List<TVSeries>>

}