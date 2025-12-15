package es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example3_Parcelable_plugin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example3_Parcelable_plugin.model.Credentials
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.screens.LoginScreen
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class LoginActivity : ComponentActivity() {

    companion object {
        const val CREDENTIALS_KEY = "credentials"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigate = { username, password ->
                            val credentials = Credentials(username, password)
                            onNavigate(credentials)
                        }
                    )
                }
            }
        }
    }

    fun onNavigate(credentials: Credentials) {
        Intent(this, LoggedActivity::class.java)
            .apply {
                putExtra(CREDENTIALS_KEY, credentials)
            }.also { intent ->
                startActivity(intent)
            }
    }
}