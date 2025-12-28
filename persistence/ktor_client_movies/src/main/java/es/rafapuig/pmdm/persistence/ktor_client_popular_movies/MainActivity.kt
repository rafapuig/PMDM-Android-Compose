package es.rafapuig.pmdm.persistence.ktor_client_popular_movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.produceState
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiServiceImpl
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.toDomain
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {

    val api = TMDBApiServiceImpl.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val movies = produceState(emptyList()) {
                value = api.getTopRatedMovies().results.map {
                    it.toDomain()
                }
            }

            PMDMComposeTheme {
                Scaffold {
                    LazyColumn(
                        contentPadding = it
                    ) {
                        items(movies.value.size) {
                            Text(text = movies.value[it].title)
                        }
                    }
                }
            }
        }
    }
}