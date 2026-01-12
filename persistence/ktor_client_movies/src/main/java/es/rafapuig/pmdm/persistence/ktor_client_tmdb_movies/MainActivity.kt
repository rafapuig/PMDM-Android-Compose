package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.TMDBApiServiceImpl
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.mappers.toDomain
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {

    val api = TMDBApiServiceImpl.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val movies by produceState(emptyList()) {
                /** En value se establece el valor del estado observable por Compose */
                value = api.fetchTopRatedMovies().results
                    .map { it.toDomain() } // Para convertir los MovieDto a objetos del modelo del dominio Movie
            }

            PMDMComposeTheme {
                Scaffold { innerPadding ->
                    LazyColumn(
                        //modifier = Modifier.statusBarsPadding(),
                        contentPadding = innerPadding
                    ) {
                        items(movies.size) { index ->
                            Text(text = movies[index].title)
                        }
                    }
                }
            }
        }
    }
}