package es.rafapuig.pmdm.clean.authentication

import es.rafapuig.pmdm.clean.authentication.auth.data.mapper.toDomain
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginResponse
import junit.framework.Assert.assertEquals
import org.junit.Test

class AuthMapperTest {

    @Test
    fun `LoginResponse maps correctly to User`() {
        val dto = LoginResponse("1", "test@mail.com", "token123")

        val user = dto.toDomain()

        assertEquals("1", user.id)
        assertEquals("test@mail.com", user.email)
        assertEquals("token123", user.token)
    }


}