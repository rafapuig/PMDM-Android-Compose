package es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.Relation

@Entity(
    tableName = "tv_series_genre_cross_ref",
    primaryKeys = ["tvSeriesId", "genreId"],
    foreignKeys = [
        ForeignKey(
            entity = TVSeriesEntity::class,
            parentColumns = ["id"],
            childColumns = ["tvSeriesId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["id"],
            childColumns = ["genreId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TVSeriesGenreCrossRef(
    val tvSeriesId: Int,
    val genreId: Int
)


data class TVSeriesWithGenres(
    @Embedded val tvSeries: TVSeriesEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            TVSeriesGenreCrossRef::class,
            parentColumn = "tvSeriesId",
            entityColumn = "genreId"
        )
    )
    val genres: List<GenreEntity>
)


data class GenreWithTVSeries(
    @Embedded val genre: GenreEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            TVSeriesGenreCrossRef::class,
            parentColumn = "genreId",
            entityColumn = "tvSeriesId"
        )
    )
    val tvSeries: List<TVSeriesEntity>
)