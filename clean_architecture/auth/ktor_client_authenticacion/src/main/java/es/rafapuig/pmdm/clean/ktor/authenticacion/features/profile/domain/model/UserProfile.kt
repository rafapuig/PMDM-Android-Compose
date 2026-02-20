package es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.model

@Serializable
data class UserProfile(val id: String, val username: String, val email: String)