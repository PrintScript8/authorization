package austral.ingsis.authorization

import austral.ingsis.authorization.service.AuthorizationService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class AuthorizationApplicationTests {

    @Autowired
    private lateinit var authorizationService: AuthorizationService

    @Test
    fun `validateAuth0Token should return userId for valid token`() {
        val id: String? = authorizationService.validateAuth0Token(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2Rldi01emRjMmxsY203b214cnIzLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NzBiZTE3MzRhNTNiNDQzN2NkZTg4NmIiLCJhdWQiOlsiaHR0cHM6Ly9TbmlwcGV0U2VyY2hlci1BUEkyLyJdLCJpYXQiOjE3MzEwODg0MDEsImV4cCI6MTczMTE3NDgwMSwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsImF6cCI6IjFuRERlbjZWN1NqamdLRE1EVnRmdmQ5OFNydUhMd3NtIn0.VbWZm_WpF4yV3b7F77ynm0b6hxIo9MjKcbC24Ghdn5Q"
        )
        assertTrue(id != null)
    }

    @Test
    fun `validateAuth0Token should return null for invalid token`() {
        val id: String? = authorizationService.validateAuth0Token(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpclolxdvbS8iLCJzdWIiOiJhdXRoMHw2NzBiZTE3MzRhNTNiNDQzN2NkZTg4NmIiLCJhdWQiOlsiaHR0cHM6Ly9TbmlwcGV0U2VyY2hlci1BUEkyLyJdLCJpYXQiOjE3MzEwODg0MDEsImV4cCI6MTczMTE3NDgwMSwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsImF6cCI6IjFuRERlbjZWN1NqamdLRE1EVnRmdmQ5OFNydUhMd3NtIn0.VbWZm_WpF4yV3b7F77ynm0b6hxIo9MjKcbC24Ghdn5Q"
        )
        assertTrue(id == null)
    }
}