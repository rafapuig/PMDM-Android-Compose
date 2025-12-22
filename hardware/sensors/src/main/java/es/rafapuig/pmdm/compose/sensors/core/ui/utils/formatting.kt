package es.rafapuig.pmdm.compose.sensors.core.ui.utils

fun Float.format(digits: Int) = "%.${digits}f".format(this)