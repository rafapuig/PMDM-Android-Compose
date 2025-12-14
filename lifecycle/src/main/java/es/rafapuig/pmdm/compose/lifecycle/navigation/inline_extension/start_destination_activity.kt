package es.rafapuig.pmdm.compose.lifecycle.navigation.inline_extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable

/** Extensiones inline para startActivity (la más usada) */

inline fun <reified T : Activity> Context.startActivity(
    vararg extras: Pair<String, Any?>
) {
    val intent = Intent(this, T::class.java)
    for ((key, value) in extras) {
        when (value) {
            is Int -> intent.putExtra(key, value)
            is String -> intent.putExtra(key, value)
            is Boolean -> intent.putExtra(key, value)
            is Parcelable -> intent.putExtra(key, value)
            null -> intent.putExtra(key, null as String?)
        }
    }
    startActivity(intent)
}