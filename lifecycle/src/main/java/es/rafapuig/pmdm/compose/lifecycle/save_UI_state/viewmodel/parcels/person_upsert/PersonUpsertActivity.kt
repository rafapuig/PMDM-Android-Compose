package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.lifecycle.ui.theme.PMDMComposeTheme

class PersonUpsertActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                PersonUpsertScreenRoot(viewModel = viewModel())
            }
        }
    }
}