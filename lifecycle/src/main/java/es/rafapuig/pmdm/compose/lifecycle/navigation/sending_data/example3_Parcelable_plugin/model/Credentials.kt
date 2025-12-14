package es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example3_Parcelable_plugin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.models.Credentials as CredentialsInterface

/** https://developer.android.com/kotlin/parcelize */

/**
 * Hay que añadir el plugin
 * id("kotlin-parcelize")
 * al los plugin del build.gradle.kts del modulo
 */

@Parcelize
data class Credentials(
    override val username: String,
    override val password: String
) : CredentialsInterface, Parcelable
