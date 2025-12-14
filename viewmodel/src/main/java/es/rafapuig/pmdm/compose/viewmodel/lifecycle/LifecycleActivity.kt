package es.rafapuig.pmdm.compose.viewmodel.lifecycle

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.counter.ui.CounterActivity
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.LevelActivity
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.start_activities.createIntent
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.start_activities.startActivity
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.start_activities.startCounterActivity
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.LifeCycleViewModel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.MenuDestination
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.MenuEvent
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.screens.MenuScreenGrid
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.launch

class LifecycleActivity : ComponentActivity() {

    val viewModel: LifeCycleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                MenuScreenGrid(
                    onNavigate = viewModel::onMenuItemClicked
                )
            }
        }

        // Observamos eventos de navegación
        lifecycleScope.launch {
            viewModel.eventsFlow.collect { event ->
                when (event) {
                    is MenuEvent.Navigate -> {
                        when (event.destination) {

                            MenuDestination.Sensors -> startActivity<LevelActivity>()

                            /** Estilo clasico de crear un Intent y llamar a startActivity() */
                            MenuDestination.Settings -> {
                                Intent(
                                    this@LifecycleActivity,
                                    CounterActivity::class.java
                                ).also {
                                    it.putExtras(bundleOf("userId" to 1000))
                                    startActivity(it)
                                }
                            }

                            /** Estilo con inline function de extensión de Context y reified */
                            MenuDestination.Dice -> startActivity<CounterActivity>("userId" to 1000)

                            /** Estilo con uso de bundle DSL */
                            MenuDestination.Converter -> startCounterActivity(id = 1000)

                            /** Estilo con funcion factoria */
                            MenuDestination.Counter -> {
                                CounterActivity
                                    .createIntent(this@LifecycleActivity, userId = 1000)
                                    .also {
                                        startActivity(it)
                                    }
                            }
                        }

                    }
                }
            }
        }
    }
}




