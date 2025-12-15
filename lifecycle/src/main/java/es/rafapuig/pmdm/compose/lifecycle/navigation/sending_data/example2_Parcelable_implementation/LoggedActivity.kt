package es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example2_Parcelable_implementation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.content.IntentCompat
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example2_Parcelable_implementation.LoginActivity.Companion.CREDENTIALS_KEY
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example2_Parcelable_implementation.model.Credentials
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.parcelable
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.screens.LoggedScreen
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class LoggedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val credentials = IntentCompat.getParcelableExtra(
            intent,
            CREDENTIALS_KEY,
            Credentials::class.java
        )

        /** Este API esta deprecada porque no es type-safe*/
        val credentialsDeprecated =
            intent.getParcelableExtra<Credentials>(CREDENTIALS_KEY)

        /**
         * Y mas kotlin idiomatico usando reified
         * (ver implementacion en getPacelableExtra-ktx.kt)
         */
        val credentialsReified =
            intent.parcelable<Credentials>(CREDENTIALS_KEY)


        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    LoggedScreen(
                        credentials = credentials ?: Credentials(),
                        onBack = { finish() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}