package es.rafapuig.pmdm.persistence.room.tvseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.persistence.room.tvseries.series.presentation.series_list_with_genres.TVSeriesWithGenresListScreenRoot
import es.rafapuig.pmdm.persistence.room.tvseries.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                TVSeriesWithGenresListScreenRoot()
            }
        }
    }
}