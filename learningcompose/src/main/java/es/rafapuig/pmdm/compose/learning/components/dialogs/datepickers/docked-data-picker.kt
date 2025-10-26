package es.rafapuig.pmdm.compose.learning.components.dialogs.datepickers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.getSelectedDate
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.Instant
import java.util.Calendar


private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

fun LocalDate.toMillis(zone: ZoneId = ZoneId.systemDefault()): Long =
    this.atStartOfDay(zone).toInstant().toEpochMilli()

fun Long.millisToLocalDate(): LocalDate =
    Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

@Composable
fun LocalDate.toString(formatter: DateTimeFormatter): String =
    LocalConfiguration.current.locales[0].let { locale ->
        format(formatter.withLocale(locale))
    }


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DataPickerDocked() {

    //Calendar.getInstance().timeInMillis

    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = LocalDate.now().toMillis(),
        initialDisplayMode = DisplayMode.Picker,
        yearRange = 2025..2026,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= LocalDate.now().toMillis() &&
                        utcTimeMillis.millisToLocalDate()
                            .dayOfWeek !in setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
            }

            override fun isSelectableYear(year: Int): Boolean {
                return super.isSelectableYear(year)
            }
        }
    )

    val selectedDate = datePickerState.getSelectedDate()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {

        OutlinedTextField(
            value = selectedDate?.toString(formatter) ?: "",
            onValueChange = {},
            label = { Text("Fecha de nacimiento") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Seleccionar fecha"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}