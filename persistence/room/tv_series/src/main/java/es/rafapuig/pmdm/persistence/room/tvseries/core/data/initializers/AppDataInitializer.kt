package es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Orquestador: AppDataInitializer
 *
 * Este es el cerebro 🧠
 */

class AppDataInitializer(
    private val initializers: List<DataInitializer>,
    private val applicationScope: CoroutineScope
) {

    fun initialize() {
        applicationScope.launch {
            initializers.forEach { it.initialize() }
        }
    }
}

