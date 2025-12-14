package es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.counter.ui.screens.CounterScreenRoot
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.screens.LevelScreenRoot
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.MenuDestination
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.screens.MenuScreenGrid

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "menu") {

        composable("menu") {
            MenuScreenGrid { dest ->
                navController.navigate(dest.name)
            }
        }

        composable(MenuDestination.Sensors.name) { LevelScreenRoot() }
        composable(MenuDestination.Settings.name) { CounterScreenRoot() }
        composable(MenuDestination.Dice.name) { CounterScreenRoot() }
        composable(MenuDestination.Converter.name) { CounterScreenRoot() }
        composable(MenuDestination.Counter.name) { CounterScreenRoot() }
    }
}
