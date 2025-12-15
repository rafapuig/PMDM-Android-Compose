package es.rafapuig.pmdm.compose.exercises.saving_UI_state.viewmodels.person_edit

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/** https://developer.android.com/reference/android/os/Parcelable */
/** https://developer.android.com/kotlin/parcelize */

@Parcelize
data class PersonEditUiState(
    val person: Person? = null,
    @IgnoredOnParcel val isLoading: Boolean = false
) : Parcelable {

    companion object {
        val Empty = PersonEditUiState()
    }
}
