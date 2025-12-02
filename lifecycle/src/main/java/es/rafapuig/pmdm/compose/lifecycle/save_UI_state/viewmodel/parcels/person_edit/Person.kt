package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_edit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val age: Int?
) : Parcelable

