package es.rafapuig.pmdm.di.counter.hilt

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.rafapuig.pmdm.di.counter.common.data.CounterDataStore
import es.rafapuig.pmdm.di.counter.common.data.CounterRepositoryImpl
import es.rafapuig.pmdm.di.counter.common.domain.repositories.CounterRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CounterModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("counter.preferences") }
        )
        //return context.applicationContext.counterDataStore
    }

    @Provides
    @Singleton
    fun provideCounterDataStore(
        dataStore: DataStore<Preferences>
    ): CounterDataStore {
        return CounterDataStore(dataStore)
    }

    @Provides
    @Singleton
    fun provideCounterRepository(
        counterDataStore: CounterDataStore
    ): CounterRepository {
        return CounterRepositoryImpl(counterDataStore)
    }

}