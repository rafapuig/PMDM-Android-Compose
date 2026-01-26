package es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.usecase

import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.model.UserProfile
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.repository.ProfileRepository

class ProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(): UserProfile = repository.getProfile()
}