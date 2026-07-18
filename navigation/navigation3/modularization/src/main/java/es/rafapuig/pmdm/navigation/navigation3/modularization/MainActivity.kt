package es.rafapuig.pmdm.navigation.navigation3.modularization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.navigation.navigation3.modularization.auth.impl.navigation.authEntryBuilder
import es.rafapuig.pmdm.navigation.navigation3.modularization.main.impl.navigation.mainEntryBuilder
import es.rafapuig.pmdm.navigation.navigation3.modularization.navigation.NavigationRoot
import es.rafapuig.pmdm.navigation.navigation3.modularization.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                NavigationRoot(
                    setOf(
                        { authEntryBuilder(it) },
                        { mainEntryBuilder(it) }
                    )
                )
            }
        }
    }
}