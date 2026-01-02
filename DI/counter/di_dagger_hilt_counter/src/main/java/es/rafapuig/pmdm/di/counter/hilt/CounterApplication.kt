package es.rafapuig.pmdm.di.counter.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** No olvidar de declararla en el Manifest !!! */
@HiltAndroidApp
class CounterApplication : Application()