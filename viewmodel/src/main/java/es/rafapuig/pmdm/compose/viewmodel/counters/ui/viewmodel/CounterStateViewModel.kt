package es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterStateViewModel: ViewModel() {

    var counter by mutableIntStateOf(0)
        private set

    fun increment() {
        counter++
    }

    fun decrement() {
        counter--
    }
}