package es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_series")
data class TVSeriesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val adult: Boolean = false,
    val backdropPath: String = "",
    //val genreIds: List<Int> = listOf(),
    //val originCountry: List<String> = listOf(),
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val firstAirDate: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)