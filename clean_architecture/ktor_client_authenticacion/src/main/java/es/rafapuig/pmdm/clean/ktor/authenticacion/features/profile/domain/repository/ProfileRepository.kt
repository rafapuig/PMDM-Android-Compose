package es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.repository

import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.model.UserProfile

interface ProfileRepository {
    suspend fun getProfile(): UserProfile
}