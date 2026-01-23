package es.rafapuig.pmdm.persistence.room.tvseries.genres.presentation.genres_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.repository.GenreRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class GenreListViewModel(
    private val repository: GenreRepository
) : ViewModel() {

    val genres = repository.observeGenres().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

}