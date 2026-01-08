package es.rafapuig.pmdm.clean.books.feature.book.domain

import es.rafapuig.pmdm.clean.books.core.domain.DataError
import es.rafapuig.pmdm.clean.books.core.domain.Result
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book

interface BookRepository {

    suspend fun searchBooks(
        query: String
    ) : Result<List<Book>, DataError.Remote>

}