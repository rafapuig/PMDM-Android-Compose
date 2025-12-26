package es.rafapuig.pmdm.clean.subscribers.repository_logic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.clean.subscribers.SubscribersApplication
import es.rafapuig.pmdm.clean.subscribers.repository_logic.data.SubscribersRepositoryImpl
import es.rafapuig.pmdm.clean.subscribers.repository_logic.domain.use_cases.GetCurrentSubscribersUseCase
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

        val FactoryNotSoGood = viewModelFactory {
            initializer {
                SubscribersViewModel(
                    getCurrentSubscribers = GetCurrentSubscribersUseCase(
                        repository = SubscribersRepositoryImpl()
                    )
                )
            }
        }

        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as SubscribersApplication

                SubscribersViewModel(
                    getCurrentSubscribers = app.getCurrentSubscribersUseCase
                )
            }
        }

        fun createFactory(getCurrentSubscribers: GetCurrentSubscribersUseCase) =
            viewModelFactory {
                initializer {
                    SubscribersViewModel(
                        getCurrentSubscribers = getCurrentSubscribers
                    )
                }
            }

        class SubscribersViewModelFactory(
            private val getCurrentSubscribers: GetCurrentSubscribersUseCase
        ) : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(SubscribersViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return SubscribersViewModel(getCurrentSubscribers) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

    }

}