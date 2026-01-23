package es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.providers

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetGenreDataSource
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetTvSeriesDataSource
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.AppDataInitializer
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.DatabaseProvider
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.TVSeriesDatabase
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.seeders.GenreDatabaseSeeder
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.seeders.TvSeriesDatabaseSeeder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RealDatabaseProvider(
    private val context: Context,
    private val applicationScope: CoroutineScope,
    //private val dataInitializer: AppDataInitializer
) : DatabaseProvider {

    @Volatile
    private var INSTANCE: TVSeriesDatabase? = null

    override fun getDatabase(): TVSeriesDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TVSeriesDatabase::class.java,
                "app.db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        applicationScope.launch(Dispatchers.IO) {
                            /** Aqui la base de datos ya existe */
                            /*val dao = getDatabase().genreDao
                            val genreDatabaseSeeder = GenreDatabaseSeeder(
                                assetDataSource = AssetGenreDataSource(context),
                                genreDao = dao
                            )
                            val tvSeriesDatabaseSeeder = TvSeriesDatabaseSeeder(
                                assetDataSource = AssetTvSeriesDataSource(context),
                                tvSeriesDao = getDatabase().tvSeriesDao
                            )
                            genreDatabaseSeeder.seedIfEmpty()
                            tvSeriesDatabaseSeeder.seedIfEmpty()*/
                            //dataInitializer.initializeAll()
                        }
                    }

                })
                .build()
            INSTANCE = instance
            instance
        }
    }
}
