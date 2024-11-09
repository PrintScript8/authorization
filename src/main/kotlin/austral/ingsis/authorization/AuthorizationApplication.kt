package austral.ingsis.authorization

import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthorizationApplication

fun main(args: Array<String>) {
    val dotenv = dotenv()
    runApplication<AuthorizationApplication>(args.toString())
}
