package es.rafapuig.pmdm.clean.books.feature.book.data.repository

import es.rafapuig.pmdm.clean.books.core.domain.DataError
import es.rafapuig.pmdm.clean.books.core.domain.Result
import es.rafapuig.pmdm.clean.books.core.domain.map
import es.rafapuig.pmdm.clean.books.feature.book.data.mappers.toBook
import es.rafapuig.pmdm.clean.books.feature.book.data.remote.RemoteBookDataSource
import es.rafapuig.pmdm.clean.books.feature.book.domain.BookRepository
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book

class OfflineFirstBookRepository(
    private val remoteDataSource: RemoteBookDataSource,
    //private val localDataSource: LocalBookDataSource
) : BookRepository {

    override suspend fun searchBooks(
        query: String
    ) : Result<List<Book>, DataError.Remote> =
        remoteDataSource.searchBooks(query)
            .map { response -> response.results.map { it.toBook() } }

}