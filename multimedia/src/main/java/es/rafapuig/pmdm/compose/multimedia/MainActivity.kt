package es.rafapuig.pmdm.compose.multimedia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import es.rafapuig.pmdm.compose.multimedia.ui.theme.MultimediaTheme
import es.rafapuig.pmdm.compose.multimedia.youtube.YoutubeVideoPlayerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MultimediaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    YoutubeVideoPlayerScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

   /* override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            window.insetsController?.hide(WindowInsets.Type.systemBars())
        } else {
            window.insetsController?.show(WindowInsets.Type.systemBars())
        }
    }*/

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy")
    }
}
