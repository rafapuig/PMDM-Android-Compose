package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_edit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class PersonEditActivity : ComponentActivity() {

    val viewModel by viewModels<PersonEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                PersonEditScreenRoot(viewModel)
            }
        }
    }
}
