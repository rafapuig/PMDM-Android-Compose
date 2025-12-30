package es.rafapuig.pmdm.clean.counter.app_mvi_one_off_events_as_channel_flow_with_data_store

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(
            "es.rafapuig.pmdm.clean.counter.app_mvi_one_off_events_as_channel_flow_with_data_store",
            appContext.packageName
        )
    }
}