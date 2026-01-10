package es.rafapuig.pmdm.client.youtube.lowlevel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.client.youtube.lowlevel.presentation.video_list.VideoListScreenRoot
import es.rafapuig.pmdm.client.youtube.lowlevel.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                VideoListScreenRoot()
            }
        }
    }
}