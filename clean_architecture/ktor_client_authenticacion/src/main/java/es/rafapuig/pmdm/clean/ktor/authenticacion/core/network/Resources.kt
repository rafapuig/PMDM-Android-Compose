package es.rafapuig.pmdm.clean.ktor.authenticacion.core.network

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable @Resource("/login")
class LoginResource(val username: String, val password: String)

@Serializable @Resource("/register")
class RegisterResource(val username: String, val password: String, val email: String)

@Serializable @Resource("/refresh")
class RefreshTokenResource

@Serializable @Resource("/logout")
class LogoutResource

@Serializable @Resource("/profile")
class ProfileResource

@Serializable
data class LoginResponse(val token: String)

@Serializable
data class RegisterResponse(val token: String)

@Serializable
data class RefreshResponse(val token: String)
