package es.rafapuig.pmdm.compose.lifecycle.awareness.examples.location.domain

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocationLifecycleAwareListener(
    context: Context,
    private val lifecycle: Lifecycle
) : DefaultLifecycleObserver {

    val _locationFlow = MutableStateFlow<Location?>(null)
    val location = _locationFlow.asStateFlow()


    val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    init {
        _locationFlow.tryEmit(locationManager
            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!)
    }

    val listener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            println("onLocationChanged: $location")
            val emitted = _locationFlow.tryEmit(location)
                //_locationFlow.update { location }
            println("emitted: $emitted")
        }

        override fun onProviderEnabled(provider: String) {
            enabled = true
        }

        override fun onProviderDisabled(provider: String) {
            enabled= false
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        connect()
    }

    override fun onStop(owner: LifecycleOwner) {
        disconnect()
    }

    private var enabled = false

    private fun connect() {
        if (enabled) return
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            println("Encendiendo el listener...")
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                100,
                1f,
                listener
            )
        }
    }

    private fun disconnect() {
        if (!enabled) return
        println("Apagando el listener...")
        locationManager.removeUpdates(listener)
    }

}