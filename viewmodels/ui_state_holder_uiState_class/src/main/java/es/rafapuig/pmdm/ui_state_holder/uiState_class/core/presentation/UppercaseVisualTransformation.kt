package es.rafapuig.pmdm.ui_state_holder.uiState_class.core.presentation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.util.Locale

class UpperCaseVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = AnnotatedString(text.text.uppercase(Locale.getDefault())),
            offsetMapping = OffsetMapping.Identity
        )
    }
}
