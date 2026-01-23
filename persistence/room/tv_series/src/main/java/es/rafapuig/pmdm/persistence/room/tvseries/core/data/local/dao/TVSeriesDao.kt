package es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.TVSeriesEntity
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.TVSeriesGenreCrossRef
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.TVSeriesWithGenres
import kotlinx.coroutines.flow.Flow

@Dao
interface TVSeriesDao : BaseDao<TVSeriesEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRefs(refs: List<TVSeriesGenreCrossRef>)

    @Query("SELECT * FROM tv_series")
    fun getAll(): List<TVSeriesEntity>

    @Query("SELECT * FROM tv_series ORDER BY name ASC")
    fun getAllSortedByName(): List<TVSeriesEntity>

    @get:Query("SELECT * FROM tv_series")
    val tvSeriesFlow: Flow<List<TVSeriesEntity>>

    @Transaction
    @Query("SELECT * FROM tv_series")
    fun observeTvSeriesWithGenres(): Flow<List<TVSeriesWithGenres>>

    @Query("SELECT * FROM tv_series WHERE id = :id")
    fun getById(id: Long): TVSeriesEntity

    @Query("SELECT * FROM tv_series WHERE name LIKE '%' || :name ")
    fun findByName(name: String): List<TVSeriesEntity>

    @Query("DELETE FROM tv_series WHERE id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM tv_series")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM tv_series")
    suspend fun count(): Long
}