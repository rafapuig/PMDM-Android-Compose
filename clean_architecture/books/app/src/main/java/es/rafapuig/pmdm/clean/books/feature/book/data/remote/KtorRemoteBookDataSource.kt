package es.rafapuig.pmdm.clean.books.feature.book.data.remote

import es.rafapuig.pmdm.clean.books.core.data.safeCall
import es.rafapuig.pmdm.clean.books.core.domain.DataError
import es.rafapuig.pmdm.clean.books.core.domain.Result
import es.rafapuig.pmdm.clean.books.feature.book.data.remote.dto.SearchResponseDto
import es.rafapuig.pmdm.clean.books.feature.book.domain.model.Book
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) : RemoteBookDataSource {

    companion object {
        private const val BASE_URL = "https://openlibrary.org"
    }

    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/search.json") {
                parameter("q", query)
                resultLimit?.let { parameter("limit", it) }
                parameter("language","eng")
                parameter("fields", "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count")
            }
        }
    }
}