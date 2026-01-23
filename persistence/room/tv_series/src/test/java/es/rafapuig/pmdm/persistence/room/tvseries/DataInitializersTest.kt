package es.rafapuig.pmdm.persistence.room.tvseries

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.AppDataInitializer
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.DataInitializer
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DataInitializersTest {

    @Test
    fun dataInitializer_runs_all_initializers_in_order() = runTest {
        val calls = mutableListOf<String>()

        val initializer = AppDataInitializer(
            listOf(
                object : DataInitializer {
                    override suspend fun initialize() {
                        calls.add("genres")
                    }
                },
                object : DataInitializer {
                    override suspend fun initialize() {
                        calls.add("series")
                    }
                }
            )
        )

        initializer.initializeAll()

        assertEquals(listOf("genres", "series"), calls)
    }

}