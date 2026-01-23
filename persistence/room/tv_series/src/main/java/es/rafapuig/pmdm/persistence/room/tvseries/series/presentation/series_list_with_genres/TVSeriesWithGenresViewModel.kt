package es.rafapuig.pmdm.persistence.room.tvseries.series.presentation.series_list_with_genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.repository.TVSeriesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class TVSeriesWithGenresViewModel(
    private val tvSeriesRepository: TVSeriesRepository
) : ViewModel() {

    val tvSeriesWithGenres = tvSeriesRepository.tvSeriesWithGenres.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )




}