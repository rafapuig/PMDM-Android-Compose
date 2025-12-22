package es.rafapuig.pmdm.compose.learning.components.dialogs.timepickers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedTimePicker(
    onConfirm: (LocalTime) -> Unit = {},
    onDismiss: () -> Unit = {},
) {

    val currentTime = LocalTime.now()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.hour,
        initialMinute = currentTime.minute,
        is24Hour = true,
    )

    /** Determines whether the time picker is dial or input */
    var showDial by remember { mutableStateOf(true) }

    /** The icon used for the icon button that switches from dial to input */
    val toggleIcon = with(Icons.Filled) {
        if (showDial) EditCalendar else AccessTime
    }


    val toggle = @Composable {
        IconButton(onClick = { showDial = !showDial }) {
            Icon(
                imageVector = toggleIcon,
                contentDescription = "Time picker type toggle",
            )
        }
    }

    AdvancedTimePickerDialogTemplate(
        onDismiss = onDismiss,
        onConfirm = {
            with(timePickerState) {
                onConfirm(LocalTime.of(hour, minute))
            }
        },
        toggle = toggle
    ) {
        if (showDial) TimePicker(state = timePickerState)
        else TimeInput(state = timePickerState)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun AdvancedTimePickerPreview(showDialog: Boolean = false) {
    var showDialog by remember { mutableStateOf(showDialog) }
    var time: LocalTime by remember { mutableStateOf(LocalTime.now()) }

    val timeFormatter =
        DateTimeFormatter.ofPattern("h:mm a")
            .withLocale(LocalConfiguration.current.locales[0])

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { showDialog = true },
        contentAlignment = Alignment.Center
    ) {

        Text(time.format(timeFormatter),
            style = MaterialTheme.typography.headlineMedium)
    }

    val onDismiss = { showDialog = false }

    if (showDialog) {
        AdvancedTimePicker(
            onDismiss = onDismiss,
            onConfirm = { selectedTime ->
                time = selectedTime
                onDismiss()
            }
        )
    }
}