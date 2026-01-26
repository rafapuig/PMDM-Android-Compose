package es.rafapuig.pmdm.clean.authentication.main.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.LogoutUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _eventChannel = Channel<HomeUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    fun onLogout() {
        viewModelScope.launch {
            logoutUseCase()
           // _eventChannel.send(HomeUiEvent.LoggedOut)
        }
    }
}

sealed interface HomeEvent {
    data object LoggedOut : HomeEvent
}
