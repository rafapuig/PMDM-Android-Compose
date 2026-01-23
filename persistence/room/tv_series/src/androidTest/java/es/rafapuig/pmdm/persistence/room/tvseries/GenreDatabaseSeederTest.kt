package es.rafapuig.pmdm.persistence.room.tvseries

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetGenreDataSource
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.TVSeriesDatabase
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao.GenreDao
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.providers.InMemoryDatabaseProvider
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.seeders.GenreDatabaseSeeder
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GenreDatabaseSeederTest {

    lateinit var context: Context
    lateinit var db: TVSeriesDatabase
    lateinit var genreDao: GenreDao

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()
        db = InMemoryDatabaseProvider(context).getDatabase()
        genreDao = db.genreDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testGenreDatabaseSeeder() = runTest {
        val dataSource = AssetGenreDataSource(context)
        val seeder = GenreDatabaseSeeder(dataSource, genreDao)

        seeder.seedIfEmpty()
        val genres = genreDao.getAll()

        assertTrue(genres.isNotEmpty())
        assertTrue(genres.map { it.name }.contains("Comedia"))
        assertTrue(genres.size == 16)
    }

}