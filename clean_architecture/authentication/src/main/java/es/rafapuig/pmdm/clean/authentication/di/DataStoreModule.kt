package es.rafapuig.pmdm.clean.authentication.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import es.rafapuig.pmdm.clean.authentication.auth.data.datasource.AuthTokenDataSource
import es.rafapuig.pmdm.clean.authentication.auth.data.fake.FakeAuthTokenDataSource
import es.rafapuig.pmdm.clean.authentication.auth.data.local.AuthLocalDataSource
import es.rafapuig.pmdm.clean.authentication.core.datastore.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {

    single<DataStore<Preferences>> {
        androidContext().dataStore
    }

    single {
        AuthLocalDataSource(get())
    }
}

val fakeDataStoreModule = module {

    single<AuthTokenDataSource> {
        FakeAuthTokenDataSource()
    }
}
