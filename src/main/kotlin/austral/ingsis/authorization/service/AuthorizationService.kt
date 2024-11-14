package austral.ingsis.authorization.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import org.springframework.stereotype.Service

@Service
class AuthorizationService {
    private val secretKey = System.getenv("AUTH0_SECRET_KEY")
    private val issuer = "https://dev-5zdc2llcm7omxrr3.us.auth0.com/"

    init {
        check(secretKey != null) { "AUTH0_SECRET_KEY is not set" }
    }

    private val algorithm = Algorithm.HMAC256(secretKey)
    private val verifier: JWTVerifier =
        JWT.require(algorithm)
            .withIssuer(issuer)
            .build()

    fun validateAuth0Token(token: String): String? {
        return try {
            val decodedJWT: DecodedJWT = verifier.verify(token)
            val userId = decodedJWT.subject
            userId
        } catch (ex: JWTVerificationException) {
            println("Invalid token: ${ex.message}")
            null
        }
    }
}
