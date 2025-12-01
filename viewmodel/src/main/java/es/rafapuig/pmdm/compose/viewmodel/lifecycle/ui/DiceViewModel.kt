package es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.domain.DiceRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiceViewModel(private val repository: DiceRepository) : ViewModel() {

    val _uiState = MutableStateFlow(DiceUiState.Empty)
    val uiState = _uiState.asStateFlow()

    private var rollingDiceJob: Job? = null

    init {
        roll()
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
            _uiState.value = _uiState.value.copy(isRolling = true)

            try {
                coroutineContext.ensureActive()
                val dice = repository.roll()

                /** Actualizar el estado con el resultado */
                this.ensureActive()
                _uiState.value = _uiState.value.copy(dice = dice)

            } catch (e: CancellationException) {
                /** Si se cancela la corrutina, no hacemos nada */
                println("Tirada cancelada!!")
                throw e
            } finally {
                _uiState.value = _uiState.value.copy(isRolling = false)
            }
        }
    }
}