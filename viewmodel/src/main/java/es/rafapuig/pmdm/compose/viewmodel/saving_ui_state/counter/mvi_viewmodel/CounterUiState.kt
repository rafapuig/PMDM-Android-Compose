package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.mvi_viewmodel

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CounterUiState(
    val counter: Int = 0,
    @IgnoredOnParcel val isLoading: Boolean = false
) : Parcelable
