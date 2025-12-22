package es.rafapuig.pmdm.compose.learning.components.dialogs.datepickers

import androidx.compose.material3.SelectableDates
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


val Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")


fun LocalDate?.toMillis(zone: ZoneId = ZoneId.systemDefault()): Long? =
    this?.let { it.atStartOfDay(zone).toInstant().toEpochMilli() }


fun Long.millisToLocalDate(): LocalDate =
    Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()


@Composable
fun LocalDate?.toString(formatter: DateTimeFormatter): String =
    LocalConfiguration.current.locales[0].let { locale ->
        this?.format(formatter.withLocale(locale)) ?: ""
    }


val selectableDates = object : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis >= LocalDate.now().toMillis()!! &&
                utcTimeMillis.millisToLocalDate()
                    .dayOfWeek !in setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    }

    override fun isSelectableYear(year: Int): Boolean {
        return super.isSelectableYear(year)
    }
}