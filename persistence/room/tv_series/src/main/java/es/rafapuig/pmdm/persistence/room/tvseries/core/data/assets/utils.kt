package es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets

import android.content.Context
import java.util.Locale

fun localizedAsset(context: Context, base: String): String {
    val lang = Locale.getDefault().language
    val path = "$lang/$base"

    return context.assets.open(path)
        .bufferedReader()
        .use { it.readText() }
}