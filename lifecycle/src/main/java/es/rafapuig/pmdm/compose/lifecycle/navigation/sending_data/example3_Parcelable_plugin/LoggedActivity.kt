package es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example3_Parcelable_plugin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example3_Parcelable_plugin.LoginActivity.Companion.CREDENTIALS_KEY
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example3_Parcelable_plugin.model.Credentials
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.parcelable
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.screens.LoggedScreen
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class LoggedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val credentials =
            intent.parcelable<Credentials>(CREDENTIALS_KEY)

        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    LoggedScreen(
                        credentials = credentials ?: Credentials.EMPTY,
                        onBack = { finish() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}