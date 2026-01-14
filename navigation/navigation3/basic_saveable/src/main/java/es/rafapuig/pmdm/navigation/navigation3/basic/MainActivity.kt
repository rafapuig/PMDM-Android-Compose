package es.rafapuig.pmdm.navigation.navigation3.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.navigation.navigation3.basic.navigation.NavigationRoot
import es.rafapuig.pmdm.navigation.navigation3.basic.ui.theme.PMDMComposeTheme

/**
 * En este caso cuando rotemos la pantalla no volvemos a la pantalla incial
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                NavigationRoot()
            }
        }
    }
}