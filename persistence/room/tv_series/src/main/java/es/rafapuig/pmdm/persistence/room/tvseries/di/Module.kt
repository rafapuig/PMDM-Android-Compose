package es.rafapuig.pmdm.persistence.room.tvseries.di

import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetGenreDataSource
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetTvSeriesDataSource
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.AppDataInitializer
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.DataInitializer
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.GenreDataInitializer
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.TvSeriesDataInitializer
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.DatabaseProvider
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.local.providers.RealDatabaseProvider
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.repository.GenreRepositoryImpl
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.seeders.GenreDatabaseSeeder
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.seeders.TvSeriesDatabaseSeeder
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.repository.TVSeriesRepositoryImpl
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.repository.GenreRepository
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.repository.TVSeriesRepository
import es.rafapuig.pmdm.persistence.room.tvseries.genres.presentation.genres_list.GenreListViewModel
import es.rafapuig.pmdm.persistence.room.tvseries.series.presentation.series_list_with_genres.TVSeriesWithGenresViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    // Scope global
    single { CoroutineScope(SupervisorJob() + Dispatchers.IO) }

    // Database + DAOs
    single<DatabaseProvider> {
        RealDatabaseProvider(androidContext(), get())//, get())
    }
    single { get<DatabaseProvider>().getDatabase().genreDao }
    single { get<DatabaseProvider>().getDatabase().tvSeriesDao }

    // Asset data sources
    single { AssetGenreDataSource(androidContext()) }
    single { AssetTvSeriesDataSource(androidContext()) }

    // DataSources
    single { AssetGenreDataSource(get()) }
    single { AssetTvSeriesDataSource(get()) }

    // Seeders
    single { GenreDatabaseSeeder(get(), get()) }
    single { TvSeriesDatabaseSeeder(get(), get()) }

    // Initializers individuales
    single<DataInitializer>(named("genres")) {
        GenreDataInitializer(get())
    }
    single<DataInitializer>(named("tvSeries")) {
        TvSeriesDataInitializer(get())
    }

    // Orquestador (orden explícito)
    single {
        AppDataInitializer(
            listOf(
                get(named("genres")),     // 1️⃣ primero géneros
                get(named("tvSeries"))    // 2️⃣ luego series
            ),
            get()
        )
    }

    single<GenreRepository> { GenreRepositoryImpl(get(), get()) }

    viewModel { GenreListViewModel(get()) }

    single<TVSeriesRepository> { TVSeriesRepositoryImpl(get()) }

    viewModelOf(::TVSeriesWithGenresViewModel)

}