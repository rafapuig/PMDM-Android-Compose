package es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para las operaciones relacionadas con los generos de Series de TV
 */

@Dao
interface GenreDao : BaseDao<GenreEntity> {

    @Query("SELECT * FROM genres")
    suspend fun getAll(): List<GenreEntity>

    @Query("SELECT * FROM genres WHERE id = :id")
    suspend fun getById(id: Long): GenreEntity?

    @Query("SELECT * FROM genres WHERE name = :name")
    suspend fun getByName(name: String): GenreEntity

    @Query("SELECT * FROM genres WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<Long>): List<GenreEntity>

    @get:Query("SELECT * FROM genres")
    val genres : Flow<List<GenreEntity>>

    @Query("SELECT COUNT(*) FROM genres")
    suspend fun count() : Int

}