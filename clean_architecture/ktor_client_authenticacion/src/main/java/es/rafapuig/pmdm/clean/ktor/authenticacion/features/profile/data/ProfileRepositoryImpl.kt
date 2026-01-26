package es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.data

import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.model.UserProfile
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(private val remote: ProfileRemoteDataSource) : ProfileRepository {
    override suspend fun getProfile(): UserProfile = remote.getProfile()
}