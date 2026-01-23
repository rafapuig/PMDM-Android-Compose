package es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.seeders.GenreDatabaseSeeder

class GenreDataInitializer(
    private val seeder: GenreDatabaseSeeder
) : DataInitializer {

    override suspend fun initialize() {
        seeder.seedIfEmpty()
    }
}
