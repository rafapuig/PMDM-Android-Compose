package es.rafapuig.pmdm.persistence.room.tvseries

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetGenreDataSource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AssetGenreDataStoreTest {

    @Test
    fun testGetAssetGenre() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        val assetGenreDataStore = AssetGenreDataSource(context)

        val genres = assetGenreDataStore.loadGenres()

        assert(genres.isNotEmpty())
        assertTrue(genres.map { it.name }.contains("Comedia"))
        assertEquals(16, genres.size)
    }

}