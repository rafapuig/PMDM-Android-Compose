package es.rafapuig.pmdm.clean.books.feature.book.data.remote

import es.rafapuig.pmdm.clean.books.core.domain.DataError
import es.rafapuig.pmdm.clean.books.feature.book.data.remote.dto.SearchResponseDto
import es.rafapuig.pmdm.clean.books.core.domain.Result

interface RemoteBookDataSource {

    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ) : Result<SearchResponseDto, DataError.Remote>

}