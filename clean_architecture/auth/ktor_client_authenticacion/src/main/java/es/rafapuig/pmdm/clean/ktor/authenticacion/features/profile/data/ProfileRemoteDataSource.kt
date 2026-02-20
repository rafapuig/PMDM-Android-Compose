package es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.data

import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.ProfileResource
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.model.UserProfile
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get


interface ProfileRemoteDataSource {
    suspend fun getProfile(): UserProfile
}

class ProfileRemoteDataSourceImpl(private val client: HttpClient) : ProfileRemoteDataSource {
    override suspend fun getProfile(): UserProfile =
        client.get(ProfileResource()).body()
}
