package es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.start_activities

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.counter.ui.CounterActivity

/** Uso de Bundle DSL (Google recomienda esto) */
fun Context.startCounterActivity(id: Int = 0, name: String = "") {
    startActivity(
        Intent(this, CounterActivity::class.java).apply {
            putExtras(
                bundleOf(
                    "id" to id,
                    "name" to name
                )
            )
        }
    )
}