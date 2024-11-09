package austral.ingsis.authorization.controller

import austral.ingsis.authorization.service.AuthorizationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/authorize")
class AuthorizationController {

    private val authorizationService = AuthorizationService();

    @PostMapping("/auth0")
    fun auth0Authorization(
        @RequestHeader("Authorization", required = false, defaultValue = "") token: String
    ): ResponseEntity<Map<String, Any?>> {
        if (token.isEmpty()) {
            return ResponseEntity(mapOf("status" to "invalid", "id" to null), HttpStatus.UNAUTHORIZED)
        }

        val id: String? = authorizationService.validateAuth0Token(token)

        val response = if (id != null) {
            mapOf("status" to "valid", "id" to id)
        } else {
            mapOf("status" to "invalid", "id" to null)
        }

        return ResponseEntity(response, if (id != null) HttpStatus.OK else HttpStatus.UNAUTHORIZED)
    }
}
