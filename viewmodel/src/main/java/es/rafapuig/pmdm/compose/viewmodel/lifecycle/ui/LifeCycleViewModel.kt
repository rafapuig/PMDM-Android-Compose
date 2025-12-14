package es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.counter.ui.CounterActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

enum class MenuDestination {
    Sensors,
    Settings,
    Dice,
    Converter,
    Counter   // ← nuevo
}

// Eventos que pueden emitir
sealed class MenuEvent {
    data class Navigate(val destination: MenuDestination) : MenuEvent()
}


class LifeCycleViewModel() : ViewModel() {

    // Channel para eventos únicos de navegación
    private val _eventChannel = Channel<MenuEvent>(Channel.BUFFERED)
    val eventsFlow = _eventChannel.receiveAsFlow() // Flow público


    // Función que llama cuando se hace click en un item
    fun onMenuItemClicked(destination: MenuDestination) {
        viewModelScope.launch {
            _eventChannel.send(MenuEvent.Navigate(destination))
        }
    }

}