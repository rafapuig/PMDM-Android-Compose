package es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.model.UserProfile
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.usecase.ProfileUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface ProfileIntent { object LoadProfile : ProfileIntent }

data class ProfileState(val isLoading: Boolean = false, val profile: UserProfile? = null, val error: String? = null)

class ProfileViewModel(private val useCase: ProfileUseCase) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun process(intent: ProfileIntent) {
        when(intent) {
            is ProfileIntent.LoadProfile -> loadProfile()
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val profile = useCase()
                _state.value = _state.value.copy(isLoading = false, profile = profile)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}
