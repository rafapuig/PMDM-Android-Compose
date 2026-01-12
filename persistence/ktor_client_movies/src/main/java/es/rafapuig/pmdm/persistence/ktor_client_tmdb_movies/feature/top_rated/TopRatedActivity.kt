package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.feature.top_rated

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.feature.top_rated.presentation.screens.TopRatedMoviesScreenRoot
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.ui.theme.PMDMComposeTheme

class TopRatedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                TopRatedMoviesScreenRoot()
            }
        }
    }
}