package es.rafapuig.pmdm.compose.exercises.saving_UI_state.viewmodels.person_edit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.compose.exercises.ui.theme.ComposeExercisesTheme

class PersonEditActivity : ComponentActivity() {

    val viewModel by viewModels<PersonEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeExercisesTheme {
                PersonEditScreenRoot(viewModel)
            }
        }
    }
}
