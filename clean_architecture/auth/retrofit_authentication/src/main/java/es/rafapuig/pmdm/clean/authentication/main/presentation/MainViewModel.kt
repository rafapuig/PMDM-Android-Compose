package es.rafapuig.pmdm.clean.authentication.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.IsUserLoggedInUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val isUserLoggedIn: IsUserLoggedInUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<StartUiState>(StartUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            //delay(3000)
            val logged = isUserLoggedIn()
            _uiState.value =
                if (logged) StartUiState.Logged
                else StartUiState.NotLogged
        }
    }
}
