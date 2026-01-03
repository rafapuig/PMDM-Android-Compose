package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.data.remote.TMDBApiServiceImpl
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.data.remote.toDomain
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {

    val api = TMDBApiServiceImpl.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val movies by produceState(emptyList()) {
                value = api.fetchTopRatedMovies().results.map {
                    it.toDomain()
                }
            }

            PMDMComposeTheme {
                Scaffold {
                    LazyColumn(
                        contentPadding = it
                    ) {
                        items(movies.size) {
                            Text(text = movies[it].title)
                        }
                    }
                }
            }
        }
    }
}