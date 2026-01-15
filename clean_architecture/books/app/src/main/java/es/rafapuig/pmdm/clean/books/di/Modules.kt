package es.rafapuig.pmdm.clean.books.di

import es.rafapuig.pmdm.clean.books.core.data.HttpClientFactory
import es.rafapuig.pmdm.clean.books.feature.book.data.remote.KtorRemoteBookDataSource
import es.rafapuig.pmdm.clean.books.feature.book.data.remote.RemoteBookDataSource
import es.rafapuig.pmdm.clean.books.feature.book.data.repository.OfflineFirstBookRepository
import es.rafapuig.pmdm.clean.books.feature.book.domain.BookRepository
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_detail.BookDetailViewModel
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.BookListViewModel
import es.rafapuig.pmdm.clean.books.feature.book.presentation.book_list.SelectedBookViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single<HttpClientEngine> { Android.create() }

    single<HttpClient> { HttpClientFactory().create(get()) }

    //singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()

    single<RemoteBookDataSource> { KtorRemoteBookDataSource(get()) }

    single<BookRepository> { OfflineFirstBookRepository(get()) }


    // https://insert-koin.io/docs/reference/koin-android/viewmodel#declaring-viewmodels
    //viewModel { BookListViewModel(get()) }

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}