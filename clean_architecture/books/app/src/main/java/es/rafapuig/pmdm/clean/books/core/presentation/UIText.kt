package es.rafapuig.pmdm.clean.books.core.presentation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UIText {
    @JvmInline
    value class DynamicString(val value: String) : UIText
    class StringResource(@param:StringRes val id:Int, val args: Array<Any> = emptyArray()) : UIText

    @Composable
    fun asString(): String = when (this) {
        is DynamicString -> value
        is StringResource -> stringResource(id, *args)
    }
}