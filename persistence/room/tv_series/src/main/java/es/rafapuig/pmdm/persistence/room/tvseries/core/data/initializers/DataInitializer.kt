package es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers

interface DataInitializer {
    suspend fun initialize()
}