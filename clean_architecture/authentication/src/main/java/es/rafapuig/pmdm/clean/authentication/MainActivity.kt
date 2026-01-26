package es.rafapuig.pmdm.clean.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.clean.authentication.navigation.LoginKey
import es.rafapuig.pmdm.clean.authentication.navigation.MainKey
import es.rafapuig.pmdm.clean.authentication.navigation.NavigationRoot
import es.rafapuig.pmdm.clean.authentication.main.presentation.MainViewModel
import es.rafapuig.pmdm.clean.authentication.main.presentation.StartUiState
import es.rafapuig.pmdm.clean.authentication.ui.theme.PMDMComposeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.value is StartUiState.Loading
        }

        setContent {

            PMDMComposeTheme {

                val state by viewModel.uiState
                    .collectAsStateWithLifecycle()

                if (state != StartUiState.Loading) {
                    val startDestination = when (state) {
                        StartUiState.Logged -> MainKey
                        StartUiState.NotLogged -> LoginKey
                        else -> error("Estado inválido")
                    }
                    NavigationRoot(startDestination)
                }
            }
        }
    }
}