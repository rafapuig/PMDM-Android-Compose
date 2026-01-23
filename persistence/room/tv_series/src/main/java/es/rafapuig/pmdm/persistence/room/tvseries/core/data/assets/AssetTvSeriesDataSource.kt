package es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets

import android.content.Context
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.dto.TVSeriesDto
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.dto.TVSeriesResultsDto
import kotlinx.serialization.json.Json

class AssetTvSeriesDataSource(
    private val context: Context
) {
    private val json = Json { ignoreUnknownKeys = true }

    fun load(): List<TVSeriesDto> {
        val jsonString = localizedAsset(context, "TVSeries.json")
        val dto = json.decodeFromString<TVSeriesResultsDto>(jsonString)
        return dto.results
    }

}