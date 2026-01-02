package es.rafapuig.pmdm.di.hilt.counter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** https://developer.android.com/training/dependency-injection/hilt-android#application-class */

/** No olvidar de declararla en el Manifest !!! */
@HiltAndroidApp
class CounterApplication : Application()