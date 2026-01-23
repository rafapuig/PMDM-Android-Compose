package es.rafapuig.pmdm.persistence.room.tvseries.core.data.local

interface DatabaseProvider {
    fun getDatabase(): TVSeriesDatabase
}
