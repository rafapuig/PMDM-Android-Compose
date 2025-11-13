package es.rafapuig.pmdm.compose.exercises.layouts.calculator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class CalculatorState {
    var firstNumber: String = ""
    var secondNumber: String = ""
    var operator: String = ""
    var result: String = ""
    var isResultVisible: Boolean = false
    var isOperatorSelected: Boolean = false
    var isDecimalPointSelected: Boolean = false

}

@Composable
fun rememberCalculatorState(): MutableState<CalculatorState> {
    return remember { mutableStateOf(CalculatorState()) }
}