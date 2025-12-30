package es.rafapuig.pmdm.clean.counter.domain

import es.rafapuig.pmdm.clean.counter.domain.repositories.CounterRepository

/** Service Locator */

object RepositoryProvider {

    private lateinit var repository : CounterRepository

    fun init(repository: CounterRepository) {
        this.repository = repository
    }

    val counterRepository: CounterRepository by lazy {
        check(::repository.isInitialized) {
            "RepositoryProvider not initialized"
        }
        repository
    }
}