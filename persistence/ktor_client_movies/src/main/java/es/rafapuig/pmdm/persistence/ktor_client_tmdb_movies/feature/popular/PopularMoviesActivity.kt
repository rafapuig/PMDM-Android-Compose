package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.feature.popular

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.feature.popular.presentation.screens.PopularMoviesScreenRoot
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.ui.theme.PMDMComposeTheme

class PopularMoviesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                PopularMoviesScreenRoot()
            }
        }
    }
}