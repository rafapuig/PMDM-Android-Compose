package es.rafapuig.pmdm.clean.dictionary.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rafapuig.pmdm.clean.dictionary.core.data.repository.WordInfoRepositoryImpl
import es.rafapuig.pmdm.clean.dictionary.core.domain.repository.WordInfoRepository
import javax.inject.Singleton
/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWordInfoRepository(
        wordInfoRepositoryImpl: WordInfoRepositoryImpl
    ): WordInfoRepository

}*/