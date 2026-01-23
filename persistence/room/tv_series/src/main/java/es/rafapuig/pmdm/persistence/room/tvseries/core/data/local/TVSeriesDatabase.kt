package es.rafapuig.pmdm.persistence.room.tvseries.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao.GenreDao
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao.TVSeriesDao
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.GenreEntity
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.TVSeriesEntity
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.TVSeriesGenreCrossRef

@Database(
    entities = [
        TVSeriesEntity::class,
        GenreEntity::class,
        TVSeriesGenreCrossRef::class
    ],
    version = 2,
    exportSchema = false
)
abstract class TVSeriesDatabase : RoomDatabase() {
    abstract val tvSeriesDao: TVSeriesDao
    abstract val genreDao: GenreDao
}