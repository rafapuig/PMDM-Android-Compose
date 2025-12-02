package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert.uiState

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/** https://developer.android.com/kotlin/parcelize */

/**
 * Hay que añadir el plugin
 * id("kotlin-parcelize")
 * al los plugin del build.gradle.kts del modulo
 */

@Parcelize
data class PersonUpsertUiStateImpl(
    override val name: String = "",
    override val age: Int? = null
) : PersonUpsertUiState, Parcelable


