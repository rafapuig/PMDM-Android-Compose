package es.rafapuig.pmdm.compose.lifecycle.awareness

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class MyObserver : DefaultLifecycleObserver {

    private val TAG = "MyObserver"

    override fun onCreate(owner: LifecycleOwner) {
        Log.i(TAG, "onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.i(TAG, "onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.i(TAG, "onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.i(TAG, "onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.i(TAG, "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.i(TAG, "onDestroy")
    }


}