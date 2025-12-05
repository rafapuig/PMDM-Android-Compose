package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.data.DiceRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.DiceRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DiceViewModel(
    private val repository: DiceRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val _uiState = savedStateHandle.getMutableStateFlow("uiState", DiceUiState.Empty)
    val uiState = _uiState.asStateFlow()

    private var rollingDiceJob: Job? = null

    init {
        if (uiState.value == DiceUiState.Empty) roll()
    }

    fun onAction(action: DiceAction) {
        when (action) {
            DiceAction.Roll -> roll()
        }
    }


    /**
     * Intentarlo con un collectLatest ... */


    fun roll() {
        /** Cancelar si habia una llamada activa */
        rollingDiceJob?.cancel()

        /** Crear una nueva */
        rollingDiceJob = viewModelScope.launch(Dispatchers.IO) {

            /** Antes de comenzar activa el isRolling */
            _uiState.update { it.copy(isRolling = true) }

            try {
                coroutineContext.ensureActive()
                val dice = repository.roll().toUi()

                /** Actualizar el estado con el resultado */
                this.ensureActive()
                _uiState.update { it.copy(dice = dice) }

            } catch (e: CancellationException) {
                /** Si se cancela la corrutina, no hacemos nada */
                println("Tirada cancelada!!")
                throw e
            } finally {
                _uiState.update { it.copy(isRolling = false) }
            }
        }
    }

    companion object {

        // Definir una clave personalizada para referirse al repositorio
        val DICE_REPOSITORY_KEY =
            object : CreationExtras.Key<DiceRepository> {}

        val Factory = viewModelFactory {
            initializer {

                val repository = this[DICE_REPOSITORY_KEY] as DiceRepository

                val savedStateHandle = createSavedStateHandle()

                DiceViewModel(
                    repository = repository,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }

    object FactoryNotGood : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val savedStateHandle = extras.createSavedStateHandle()

            @Suppress("UNCHECKED_CAST")
            return DiceViewModel(
                repository = DiceRepositoryImpl(),
                savedStateHandle = savedStateHandle
            ) as T
        }
    }
}