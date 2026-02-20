package es.rafapuig.pmdm.clean.authentication.main.domain.model

data class Profile(
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String? = null
)
