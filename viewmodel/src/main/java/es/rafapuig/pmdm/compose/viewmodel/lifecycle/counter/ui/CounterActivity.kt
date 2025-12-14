package es.rafapuig.pmdm.compose.viewmodel.lifecycle.counter.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.counter.ui.screens.CounterScreenRoot
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import kotlin.jvm.java

class CounterActivity : ComponentActivity() {

    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                CounterScreenRoot()
            }
        }
    }
}