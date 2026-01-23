package es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.providers

import android.content.Context
import androidx.room.Room
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.DatabaseProvider
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.TVSeriesDatabase

class InMemoryDatabaseProvider(
    private val context: Context
) : DatabaseProvider {

    private var INSTANCE: TVSeriesDatabase? = null

    override fun getDatabase(): TVSeriesDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.inMemoryDatabaseBuilder(
                context,
                TVSeriesDatabase::class.java
            )
                .allowMainThreadQueries() // solo para tests
                .build()
            INSTANCE = instance
            instance
        }
    }
}
