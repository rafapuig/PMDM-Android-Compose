package es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.navigation.NavigationRoot
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