package es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui.start_activities

import android.content.Context
import android.content.Intent
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.counter.ui.CounterActivity

fun CounterActivity.Companion.createIntent(context: Context, userId: Int = 0): Intent {
    return Intent(context, CounterActivity::class.java).apply {
        putExtra("userId", userId)
    }
}