package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable

interface DiceViewModelProvider {

    @Composable fun provideViewModel(): DiceViewModel

    fun ComponentActivity.provideViewModelLazy() : Lazy<DiceViewModel>

}