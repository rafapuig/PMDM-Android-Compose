package es.rafapuig.pmdm.clean.dictionary.feature.dictionary.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rafapuig.pmdm.clean.dictionary.core.data.util.Resource
import es.rafapuig.pmdm.clean.dictionary.feature.dictionary.domain.use_case.GetWordInfo
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var state by mutableStateOf(WordInfoState())
        private set


    val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    init {
        savedStateHandle.get<String>("word")?.let { word ->
            _query.value = word
        }
        observeQueryChanges()
    }

    @OptIn(FlowPreview::class)
    private fun observeQueryChanges() {
        viewModelScope.launch {
            query
                .map { it.trim() } // ✂️ Normaliza el input en el ViewModel, no en la UI
                .onEach { query ->
                    if (query.isBlank()) {
                        state = state.copy(
                            wordInfoItems = emptyList()
                        )
                    }
                }
                .debounce(1.seconds)
                .distinctUntilChanged()
                .filter { it.isNotBlank() && it.length >= 2 }
                .collectLatest {
                    getWordInfo(it)
                        .onEach { result ->
                            when (result) {

                                is Resource.Success -> {
                                    state = state.copy(
                                        wordInfoItems = result.data ?: emptyList(),
                                        isLoading = false
                                    )
                                }

                                is Resource.Error<*> -> {
                                    state = state.copy(
                                        wordInfoItems = result.data ?: emptyList(),
                                        isLoading = false
                                    )
                                    _eventFlow.emit(
                                        UIEvent.ShowSnackbar(
                                            result.message ?: "Error desconocido"
                                        )
                                    )

                                }
                                is Resource.Loading<*> -> {
                                    state = state.copy(
                                        wordInfoItems = result.data ?: emptyList(),
                                        isLoading = true
                                    )
                                }
                            }
                        }.launchIn(this)
                }

        }
    }

    fun onQueryChange(query: String) {
        _query.value = query
        savedStateHandle["word"] = query
        state = state.copy(query = query)
    }

}