package es.rafapuig.pmdm.clean.authentication.auth.data.mapper

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginResponse
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterResponse
import es.rafapuig.pmdm.clean.authentication.auth.domain.model.User

fun LoginResponse.toDomain() =
    User(
        id = id,
        email = email,
        token = token
    )

fun RegisterResponse.toDomain() = User(id, email, token)
