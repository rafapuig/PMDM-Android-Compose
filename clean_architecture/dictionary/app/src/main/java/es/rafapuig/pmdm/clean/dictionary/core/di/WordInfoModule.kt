package es.rafapuig.pmdm.clean.dictionary.core.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rafapuig.pmdm.clean.dictionary.core.data.local.Converters
import es.rafapuig.pmdm.clean.dictionary.core.data.local.WordInfoDao
import es.rafapuig.pmdm.clean.dictionary.core.data.local.WordInfoDatabase
import es.rafapuig.pmdm.clean.dictionary.core.data.remote.DictionaryApi
import es.rafapuig.pmdm.clean.dictionary.core.data.repository.WordInfoRepositoryImpl
import es.rafapuig.pmdm.clean.dictionary.core.domain.repository.WordInfoRepository
import es.rafapuig.pmdm.clean.dictionary.feature.dictionary.domain.use_case.GetWordInfo
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {


    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        api: DictionaryApi,
        db: WordInfoDatabase
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

}