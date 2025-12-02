package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_edit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/** https://developer.android.com/reference/android/os/Parcelable */
/** https://developer.android.com/kotlin/parcelize */

@Parcelize
data class PersonEditUiState(
    val person: Person? = null,
    val isLoading: Boolean = false
) : Parcelable {

    companion object {
        val Empty = PersonEditUiState()
    }
}
