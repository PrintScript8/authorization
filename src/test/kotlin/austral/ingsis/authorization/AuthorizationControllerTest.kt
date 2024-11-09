package austral.ingsis.authorization

import austral.ingsis.authorization.controller.AuthorizationController
import io.github.cdimascio.dotenv.dotenv
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest
class AuthorizationControllerTest {

    companion object {
        @BeforeAll
        @JvmStatic
        fun setup() {
            val dotenv = dotenv()
            System.setProperty("AUTH0_SECRET_KEY", dotenv["AUTH0_SECRET_KEY"])
        }
    }

    @Autowired
    private lateinit var authorizationController: AuthorizationController

    @Test
    fun `auth0Authorization should return valid response for valid token`() {
        val validToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2Rldi01emRjMmxsY203b214cnIzL" +
                "nVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NzBiZTE3MzRhNTNiNDQzN2NkZTg4NmIiLCJhdW" +
                "QiOlsiaHR0cHM6Ly9TbmlwcGV0U2VyY2hlci1BUEkyLyJdLCJpYXQiOjE3MzEwODg0MDEsImV4cCI6MT" +
                "czMTE3NDgwMSwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsImF6cCI6IjFuRERlbjZWN1NqamdLR" +
                "E1EVnRmdmQ5OFNydUhMd3NtIn0.VbWZm_WpF4yV3b7F77ynm0b6hxIo9MjKcbC24Ghdn5Q"

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
