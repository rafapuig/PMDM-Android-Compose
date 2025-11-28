package es.rafapuig.pmdm.compose.viewmodel.subscribers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.compose.viewmodel.subscribers.data.repositories.SubscribersRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.subscribers.domain.uses_cases.GetCurrentSubscribersUseCase
import es.rafapuig.pmdm.compose.viewmodel.subscribers.domain.uses_cases.GetSubscribersUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.math.cos
import kotlin.time.Duration.Companion.milliseconds

class SubscribersViewModelWithDependencies(
    getCurrentSubscribersUseCase: GetSubscribersUseCase
) : ViewModel() {

    val subscribersFlow = getCurrentSubscribersUseCase()

    val subscribersState = subscribersFlow
        .stateIn(
            viewModelScope,
            SharingStarted.Companion.Lazily,
            0
        ).onStart { println("🔥 Flow caliente flowId arrancado...") }


    companion object {

        val Factory = viewModelFactory {
            initializer {
                SubscribersViewModelWithDependencies(
                    getCurrentSubscribersUseCase = GetSubscribersUseCase(
                        repository = SubscribersRepositoryImpl()
                    )
                )
            }
        }
    }
}