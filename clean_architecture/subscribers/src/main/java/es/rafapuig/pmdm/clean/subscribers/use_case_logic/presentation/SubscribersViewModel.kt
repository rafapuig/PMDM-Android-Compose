package es.rafapuig.pmdm.clean.subscribers.use_case_logic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.clean.subscribers.use_case_logic.domain.use_cases.GetCurrentSubscribersUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class SubscribersViewModel(
    getCurrentSubscribers: GetCurrentSubscribersUseCase
) : ViewModel() {

    val subscribers = getCurrentSubscribers()
        .onStart { println("🔥 Flow caliente iniciando...") }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            0
        )

    companion object {
        val Factory = viewModelFactory {
            initializer {
                SubscribersViewModel(
                    getCurrentSubscribers = GetCurrentSubscribersUseCase
                )
            }
        }
    }

}