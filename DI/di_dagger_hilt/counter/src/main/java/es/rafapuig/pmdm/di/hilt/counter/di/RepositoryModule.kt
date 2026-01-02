package es.rafapuig.pmdm.di.hilt.counter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rafapuig.pmdm.di.hilt.counter.data.CounterRepositoryImpl
import es.rafapuig.pmdm.di.hilt.counter.domain.repositories.CounterRepository
import javax.inject.Singleton

/** https://developer.android.com/training/dependency-injection/hilt-android#inject-provides */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCounterRepository(
        counterRepositoryImpl: CounterRepositoryImpl
    ): CounterRepository

}