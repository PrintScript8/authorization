package austral.ingsis.authorization

import austral.ingsis.authorization.controller.AuthorizationController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

// THIS TESTS CAN FAIL IF THE TOKEN IN THE EXAMPLE EXPIRES!!!!!!!!

@SpringBootTest
class AuthorizationControllerTest {
    @Autowired
    private lateinit var authorizationController: AuthorizationController

    @Test
    fun `auth0Authorization should return valid response for valid token`() {
        val validToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ" +
                    "odHRwczovL2Rldi01emRjMmxsY203b214cnIzLnVz" +
                    "LmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NzBiZTE" +
                    "3MzRhNTNiNDQzN2NkZTg4NmIiLCJhdWQiOlsiaHR0cHM6L" +
                    "y9TbmlwcGV0U2VyY2hlci1BUEkyLyJdLCJpYXQiOjE3MzE2MDQ" +
                    "4NTMsImV4cCI6MTczMTY5MTI1Mywic2NvcGUiOiJvcGVuaWQgcH" +
                    "JvZmlsZSBlbWFpbCIsImF6cCI6IjFuRERlbjZWN1NqamdLRE1EVn" +
                    "RmdmQ5OFNydUhMd3NtIn0.HtIKaXLNlr_6Wgfmoa6yMrJf-NHS-6NagXqWkj-vyvA"

        val response: ResponseEntity<Map<String, Any?>> = authorizationController.auth0Authorization(validToken)

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `auth0Authorization should return unauthorized response for no token`() {
        val response: ResponseEntity<Map<String, Any?>> = authorizationController.auth0Authorization("")

        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
        assertEquals(mapOf("status" to "invalid", "id" to null), response.body)
    }
}
