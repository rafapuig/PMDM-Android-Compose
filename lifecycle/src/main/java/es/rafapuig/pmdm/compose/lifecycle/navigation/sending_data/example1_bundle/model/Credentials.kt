package es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example1_bundle.model

import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.models.Credentials as CredentialsInterface

/**
 * Implementación simple como una data class
 * (no se puede empaquetar (parcelize) en un Bundle)
 */

data class Credentials(
    override val username: String,
    override val password: String
) : CredentialsInterface
