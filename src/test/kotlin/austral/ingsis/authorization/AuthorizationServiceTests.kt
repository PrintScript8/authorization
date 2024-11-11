package austral.ingsis.authorization

import austral.ingsis.authorization.service.AuthorizationService
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

// THIS TESTS CAN FAIL IF THE TOKEN IN THE EXAMPLE EXPIRES!!!!!!!!

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class AuthorizationServiceTests {
    @Autowired
    private lateinit var authorizationService: AuthorizationService

    @Test
    fun `validateAuth0Token should return userId for valid token`() {
        val id: String? =
            authorizationService.validateAuth0Token(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2Rldi01emRjMmxsY203b214cnIzLnVzL" +
                    "mF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NzBiZTE3MzRhNTNiNDQzN2NkZTg4NmIiLCJhdWQiOlsiaHR0cHM6L" +
                    "y9TbmlwcGV0U2VyY2hlci1BUEkyLyJdLCJpYXQiOjE3MzEzMDY2MjQsImV4cCI6MTczMTM5MzAyNCwic2NvcGUiO" +
                    "iJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsImF6cCI6IjFuRERlbjZWN1NqamdLRE1EVnRmdmQ5OFNydUhMd3NtIn0.2C" +
                    "nlNjhLpHXV5JJAgWDp97vuYsuEBwpnJBwCgSPQurk",
            )
        assertTrue(id != null)
    }

    @Test
    fun `validateAuth0Token should return null for invalid token`() {
        val id: String? =
            authorizationService.validateAuth0Token(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2Rldi01emRjMmxsY203b214cn" +
                    "IzLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NzBiZTE3MzRhNTNiNDQzN2NkZTg4NmIiLCJhdWQiOls" +
                    "iaHR0cHM6Ly9TbmlwcGV0U2VyY2hlcigedagedizk2MjcsImV4cCI6MTczMTI2NjA" +
                    "yNywic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsImF6cCI6IjFuRERlbjZWN1NqamdLRE1EVnRmdm" +
                    "Q5OFNydUhMd3NtIn0.sSZNuDS-frikinfraken",
            )
        assertTrue(id == null)
    }
}
