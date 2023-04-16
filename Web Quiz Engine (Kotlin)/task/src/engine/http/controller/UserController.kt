package engine.http.controller

import engine.db.model.User
import engine.http.exception.InvalidEmailException
import engine.http.exception.OccupiedEmailException
import engine.http.exception.TooShortPasswordException
import engine.http.request.RegisterRequest
import engine.services.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(
    private val service: UserService,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/api/register")
    fun registerUser(@RequestBody registerRequest: RegisterRequest) {
        if (!registerRequest.email.isValidEmail())
            throw InvalidEmailException()
        if (service.existsUserByEmail(registerRequest.email))
            throw OccupiedEmailException()
        if (registerRequest.password.length < 5) throw TooShortPasswordException()

        val user = User(registerRequest.email, passwordEncoder.encode(registerRequest.password))
        service.addUser(user)
    }
}

private fun String.isValidEmail(): Boolean = this.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
