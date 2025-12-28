package es.rafapuig.pmdm.persistence.ktor_client_popular_movies

import android.app.Application
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.MoviesRepositoryRemoteImpl
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiServiceImpl

class MoviesApplication : Application() {

    val moviesRepository by lazy {
        MoviesRepositoryRemoteImpl(
            TMDBApiServiceImpl.create()
        )
    }
}