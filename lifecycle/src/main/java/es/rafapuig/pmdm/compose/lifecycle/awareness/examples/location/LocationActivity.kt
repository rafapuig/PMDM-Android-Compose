package es.rafapuig.pmdm.compose.lifecycle.awareness.examples.location

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.compose.lifecycle.awareness.examples.location.domain.LocationLifecycleAwareListener
import es.rafapuig.pmdm.compose.lifecycle.awareness.examples.location.ui.LocationDemoScreen
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class LocationActivity : ComponentActivity() {

    lateinit var locationListener: LocationLifecycleAwareListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissions()

        locationListener = LocationLifecycleAwareListener(this, lifecycle)
        lifecycle.addObserver(locationListener)

        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                val location by locationListener.location
                    .collectAsStateWithLifecycle(null)

                LocationDemoScreen(location = location)
            }
        }
    }

    private fun requestPermissions() {
        // Ask for ACCESS_COARSE_LOCATION and ACCESS_FINE_LOCATION permissions if the app doesn't have them
        // (apart from putting it in the manifest, as of a certain version of Android
        // you must also ask for explicit permissions from the user).
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) ActivityCompat.requestPermissions(
            this, arrayOf<String>(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), 0
        )
    }
}
