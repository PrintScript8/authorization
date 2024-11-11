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
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2Rldi01emRjMmxsY203b214cnIzLnVzLmF1dGgwLmNvb" +
                "S8iLCJzdWIiOiJhdXRoMHw2NzBiZTE3MzRhNTNiNDQzN2NkZTg4NmIiLCJhdWQiOlsiaHR0cHM6Ly9TbmlwcGV0U2VyY2h" +
                "lci1BUEkyLyJdLCJpYXQiOjE3MzEzMDY2MjQsImV4cCI6MTczMTM5MzAyNCwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlb" +
                "WFpbCIsImF6cCI6IjFuRERlbjZWN1NqamdLRE1EVnRmdmQ5OFNydUhMd3NtIn0.2CnlNjhLpHXV5JJAgWDp97vuYsuEBwpn" +
                "JBwCgSPQurk"

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
