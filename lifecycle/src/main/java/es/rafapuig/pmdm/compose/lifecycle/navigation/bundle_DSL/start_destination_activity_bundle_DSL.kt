package es.rafapuig.pmdm.compose.lifecycle.navigation.bundle_DSL

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf

/** Uso de Bundle DSL (Google recomienda esto) */
fun Context.startDestinationActivity(name: String = "") {
    startActivity(
        Intent(this, DestinationActivity::class.java).apply {
            putExtras(
                bundleOf(
                    "name" to name
                )
            )
        }
    )
}