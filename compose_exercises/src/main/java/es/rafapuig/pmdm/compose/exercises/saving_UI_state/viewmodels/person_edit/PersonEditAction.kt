package es.rafapuig.pmdm.compose.exercises.saving_UI_state.viewmodels.person_edit

sealed interface PersonEditAction {
    object OnClearPerson : PersonEditAction
    data class OnSetPerson(val person: Person) : PersonEditAction
    data class OnNameChanged(val name: String) : PersonEditAction
    data class OnAgeChanged(val age: Int?) : PersonEditAction
    object OnLoadPerson : PersonEditAction
}