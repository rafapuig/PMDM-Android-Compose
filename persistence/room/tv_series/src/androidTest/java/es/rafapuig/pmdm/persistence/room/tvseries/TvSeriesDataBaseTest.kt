package es.rafapuig.pmdm.persistence.room.tvseries

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.TVSeriesDatabase
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.dao.TVSeriesDao
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities.TVSeriesEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.yield
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.collections.isNotEmpty
import kotlin.time.Duration.Companion.milliseconds

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TvSeriesDataBaseTest {


    lateinit var db: TVSeriesDatabase
    lateinit var tvSeriesDao: TVSeriesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            TVSeriesDatabase::class.java
        ).build()
        tvSeriesDao = db.tvSeriesDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertNewTVSeries() = runTest {
        val tvSeries = TVSeriesEntity(
            id = 1,
            name = "The Walking Dead"
        )
        val rowId = tvSeriesDao.insert(tvSeries)
        val tvSeriesList = tvSeriesDao.getAll()
        Assert.assertEquals(tvSeriesList.size, 1)
        Assert.assertEquals(tvSeriesList[0].name, "The Walking Dead")
        Assert.assertEquals(tvSeriesList[0].id, 1)
    }

    @Test
    fun updateTVSeries() = runTest {
        val tvSeries = TVSeriesEntity(
            id = 1,
            name = "The Walking Dead"
        )
        val rowId = tvSeriesDao.insert(tvSeries)

        tvSeriesDao.update(tvSeries.copy(adult = true))

        val tvSeriesList = tvSeriesDao.getById(rowId)
        Assert.assertEquals(tvSeriesList.name, "The Walking Dead")
        Assert.assertEquals(tvSeriesList.id, 1)
        Assert.assertEquals(tvSeriesList.adult, true)
    }

    @Test
    fun deleteTvSeries() = runTest {
        val tvSeries = TVSeriesEntity(
            id = 1,
            name = "The Walking Dead"
        )
        val rowId = tvSeriesDao.insert(tvSeries)
        tvSeriesDao.delete(tvSeries)
        val tvSeriesList = tvSeriesDao.getAll()
        Assert.assertEquals(tvSeriesList.size, 0)
    }

    @Test
    fun insertAllTvSeries() = runTest {

        val tvSeries = TVSeriesEntity(
            id = 1,
            name = "The Walking Dead"
        )
        val tvSeries2 = TVSeriesEntity(
            id = 2,
            name = "The Big Bang Theory"
        )
        val tvSeries3 = TVSeriesEntity(
            id = 3,
            name = "The Office"
        )

        val rowId = tvSeriesDao.insertAll(tvSeries, tvSeries2, tvSeries3)
        val tvSeriesList = tvSeriesDao.getAll()
        Assert.assertEquals(tvSeriesList.size, 3)
        Assert.assertEquals(tvSeriesList[0].name, "The Walking Dead")
        Assert.assertEquals(tvSeriesList[1].name, "The Big Bang Theory")
        Assert.assertEquals(tvSeriesList[2].name, "The Office")
        Assert.assertEquals(tvSeriesList[0].id, 1)
        Assert.assertEquals(tvSeriesList[1].id, 2)
        Assert.assertEquals(tvSeriesList[2].id, 3)
    }


    @Test
    fun deleteAllTvSeries() = runTest {
        val tvSeries = TVSeriesEntity(
            id = 1,
            name = "The Walking Dead"
        )
        val tvSeries2 = TVSeriesEntity(
            id = 2,
            name = "The Big Bang Theory"
        )
        val tvSeries3 = TVSeriesEntity(
            id = 3,
            name = "The Office"
        )

        val rowId = tvSeriesDao.insertAll(tvSeries, tvSeries2, tvSeries3)
        tvSeriesDao.deleteAll()

        val tvSeriesList = tvSeriesDao.getAll()
        Assert.assertEquals(tvSeriesList.size, 0)
    }

    @Test
    fun testGetAllTvSeries() = runTest {
        val tvSeries = TVSeriesEntity(
            id = 1,
            name = "The Walking Dead"
        )
        val tvSeries2 = TVSeriesEntity(
            id = 2,
            name = "The Big Bang Theory"
        )
        val tvSeries3 = TVSeriesEntity(
            id = 3,
            name = "The Office"
        )

        val tvSeriesList = tvSeriesDao.tvSeriesFlow

        coroutineScope {

            launch {
                delay(500.milliseconds)
                tvSeriesDao.insert(tvSeries)
                delay(500.milliseconds)
                tvSeriesDao.insert(tvSeries2)
                delay(500.milliseconds)
                tvSeriesDao.insert(tvSeries3)
            }

            launch {
                tvSeriesList.collect {
                    println(it)
                }
            }
        }

        Assert.assertEquals(tvSeriesList.count(), 3)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun observe_emits_after_insert() = runTest {

        val tvSeries = TVSeriesEntity(
            id = 1,
            name = "The Walking Dead"
        )

        val tvSeries2 = TVSeriesEntity(
            id = 2,
            name = "The Big Bang Theory"
        )

        val tvSeries3 = TVSeriesEntity(
            id = 3,
            name = "The Office"
        )

        val emissions = async {
            tvSeriesDao.tvSeriesFlow.take(3).toList()
        }

        advanceUntilIdle()
        tvSeriesDao.insert(tvSeries)
        delay(500.milliseconds)
        tvSeriesDao.insert(tvSeries2)
        delay(500.milliseconds)
        tvSeriesDao.insert(tvSeries3)

        val result = emissions.await()

        assertEquals(3, result.size)
        assertTrue(result[0].isNotEmpty())
        assertTrue(result[1].isNotEmpty())
        assertTrue(result[2].isNotEmpty())

        assertTrue(result[0].contains(tvSeries))
        assertTrue(result[1].contains(tvSeries2))
        assertTrue(result[2].contains(tvSeries3))

        result.forEach {
            println(it)
        }

        //assertTrue(result[0].size == 1)
        //assertTrue(result[1].size == 2)
        //assertTrue(result[2].size == 3)
    }


    /**
     * Metodos de Turbine
     * | Método                             | Qué hace                       |
     * | ---------------------------------- | ------------------------------ |
     * | `awaitItem()`                      | Espera el siguiente valor      |
     * | `awaitComplete()`                  | Espera que el Flow termine     |
     * | `awaitError()`                     | Espera un error                |
     * | `expectNoEvents()`                 | Verifica que no haya emisiones |
     * | `cancelAndIgnoreRemainingEvents()` | Limpia el test                 |
     *
     */

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun observe_emits_after_insert_turbine() = runTest {

        val tvSeries = TVSeriesEntity(
            id = 1,
            name = "The Walking Dead"
        )

        val tvSeries2 = TVSeriesEntity(
            id = 2,
            name = "The Big Bang Theory"
        )

        val tvSeries3 = TVSeriesEntity(
            id = 3,
            name = "The Office"
        )

        tvSeriesDao.tvSeriesFlow.test {
            // 1️⃣ Snapshot inicial
            val initial = awaitItem()

            // 2️⃣ Insert
            tvSeriesDao.insert(tvSeries)

            val afterFirst = awaitItem()
            assertEquals(1, afterFirst.size)

            // 3️⃣ Segundo insert
            tvSeriesDao.insert(tvSeries2)

            val afterSecond = awaitItem()
            assertEquals(2, afterSecond.size)

            // 4️⃣ Tercer insert
            tvSeriesDao.insert(tvSeries3)

            val afterThird = awaitItem()
            assertEquals(3, afterThird.size)

            // 5️⃣ Cancelamos
            cancelAndIgnoreRemainingEvents()
        }
    }

}