package es.rafapuig.pmdm.compose.exercises.saving_UI_state.viewmodels.person_edit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val age: Int?
) : Parcelable

