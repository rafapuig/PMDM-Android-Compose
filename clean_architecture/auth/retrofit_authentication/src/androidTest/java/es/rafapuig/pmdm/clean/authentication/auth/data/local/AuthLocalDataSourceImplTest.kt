package es.rafapuig.pmdm.clean.authentication.auth.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.rafapuig.pmdm.clean.authentication.core.datastore.dataStore
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class AuthLocalDataSourceImplTest {

    private lateinit var dataSource: AuthLocalDataSource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        //val dataStore = context.dataStore
        //val dataStore = mockk<DataStore<Preferences>>()

        /*val dataStore = PreferenceDataStoreFactory.create {
            File(context.filesDir, "test_datastore.preferences_pb")
        }*/
        val dataStore = PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("test_datastore.preferences")
        }


        dataSource = AuthLocalDataSourceDataStoreImpl(dataStore)
    }

    @After
    fun tearDown() = runTest {
        dataSource.clear()
    }

    @Test
    fun saveToken_saves_token_in_data_store() = runTest {

        // Given
        val token = "testToken"

        dataSource.saveToken(token)

        val result = dataSource.getToken().first()

        assertEquals(token, result)

    }

}