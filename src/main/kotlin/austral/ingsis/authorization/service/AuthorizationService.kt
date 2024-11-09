package austral.ingsis.authorization.service

import org.springframework.stereotype.Service
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import io.github.cdimascio.dotenv.dotenv

@Service
class AuthorizationService {

    private val secretKey = dotenv()["AUTH0_SECRET_KEY"] // Your HS256 secret key
    private val issuer = "https://dev-5zdc2llcm7omxrr3.us.auth0.com/" // Replace with your Auth0 issuer if different

    private val algorithm = Algorithm.HMAC256(secretKey)
    private val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(issuer)
        .build()

    fun validateAuth0Token(token: String): String? {
        return try {
            val decodedJWT: DecodedJWT = verifier.verify(token)  // Verifies token's signature and issuer
            val userId = decodedJWT.subject                      // Extracts the user ID from `sub` claim
            userId
        } catch (ex: JWTVerificationException) {
            // Token is invalid (signature, expired, or malformed)
            println("Invalid token: ${ex.message}")
            null
        }
    }
}
