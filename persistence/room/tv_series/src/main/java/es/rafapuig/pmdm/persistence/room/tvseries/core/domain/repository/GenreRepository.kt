package es.rafapuig.pmdm.persistence.room.tvseries.core.domain.repository

import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {

    fun observeGenres(): Flow<List<Genre>>

    suspend fun preloadIfEmpty()

}