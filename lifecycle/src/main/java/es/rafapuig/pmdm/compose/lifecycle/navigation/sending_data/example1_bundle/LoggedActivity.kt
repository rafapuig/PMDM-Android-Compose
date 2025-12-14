package es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example1_bundle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example1_bundle.LoginActivity.Companion.CREDENTIALS_PASSWORD_KEY
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example1_bundle.LoginActivity.Companion.CREDENTIALS_USERNAME_KEY
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example1_bundle.model.Credentials
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.screens.LoggedScreen
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class LoggedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val credentials = Credentials(
            intent.getStringExtra(CREDENTIALS_USERNAME_KEY) ?: "",
            intent.getStringExtra(CREDENTIALS_PASSWORD_KEY) ?: ""
        )

        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    LoggedScreen(
                        credentials = credentials,
                        onBack = { finish() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}