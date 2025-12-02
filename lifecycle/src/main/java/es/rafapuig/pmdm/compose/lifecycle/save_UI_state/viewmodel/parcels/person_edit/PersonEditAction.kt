package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_edit

sealed interface PersonEditAction {
    object OnClearPerson : PersonEditAction
    data class OnSetPerson(val person: Person) : PersonEditAction
    data class OnNameChanged(val name: String) : PersonEditAction
    data class OnAgeChanged(val age: Int?) : PersonEditAction
    object OnLoadPerson : PersonEditAction
}