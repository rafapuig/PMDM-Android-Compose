package es.rafapuig.pmdm.compose.learning.components.dialogs.timepickers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialTimePickerDialog(
    initialHour: Int = LocalTime.now().hour,
    initialMinute: Int = LocalTime.now().minute,
    onDismiss: () -> Unit = {},
    onConfirm: (LocalTime) -> Unit = {}
) {
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = false
    )
    TimePickerTemplateDialog(
        onDismiss = onDismiss,
        onConfirm = {
            LocalTime.of(
                timePickerState.hour,
                timePickerState.minute
            ).let { time -> onConfirm(time) }
        }
    ) {
        TimePicker(state = timePickerState)
    }
}

//@Preview
@Composable
fun DialTimePickerDialogPreview() {
    val currentTime = LocalTime.now()
    val hour = currentTime.hour
    val minute = currentTime.minute
    DialTimePickerDialog(hour, minute)
}

@Preview(showSystemUi = true)
@Composable
fun DialTimePickerDialogShowPreview() {
    var time by remember { mutableStateOf(LocalTime.now()) }

    val timeFormatter = remember {
        DateTimeFormatter.ofPattern("h:mm a")
    }

    var showTimePicker by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { showTimePicker = true },
        contentAlignment = Alignment.Center
    ) {
        Text(text = time.format(timeFormatter), fontSize = 40.sp)
    }
    if (showTimePicker) DialTimePickerDialog(
        initialHour = time.hour,
        initialMinute = time.minute,
        onDismiss = {showTimePicker = false },
        onConfirm = {
            time = it
            showTimePicker = false
        }
    )
}

