package es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.seeders.TvSeriesDatabaseSeeder

class TvSeriesDataInitializer(
    private val seeder: TvSeriesDatabaseSeeder
) : DataInitializer {

    override suspend fun initialize() {
        seeder.seedIfEmpty()
    }
}
