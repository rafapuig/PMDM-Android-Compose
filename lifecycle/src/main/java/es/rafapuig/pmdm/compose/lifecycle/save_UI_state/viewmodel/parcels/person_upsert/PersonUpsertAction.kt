package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert

sealed interface PersonUpsertAction {
    data class OnNameChanged(val name: String) : PersonUpsertAction
    data class OnAgeChanged(val age: Int?) : PersonUpsertAction
    object OnClearPerson : PersonUpsertAction
}
