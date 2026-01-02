package es.rafapuig.pmdm.di.counter.hilt.navm

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import es.rafapuig.pmdm.di.counter.common.domain.repositories.CounterRepository
import es.rafapuig.pmdm.di.counter.hilt.navm.presentation.CounterViewModelFactory

@Module
@InstallIn(ActivityComponent::class)
object CounterViewModelModule {

    @Provides
    fun provideCounterViewModelFactory(
        repository: CounterRepository
    ): CounterViewModelFactory {
        return CounterViewModelFactory(repository)
    }
}
