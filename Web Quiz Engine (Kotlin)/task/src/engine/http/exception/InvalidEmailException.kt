package engine.http.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.server.ResponseStatusException

class InvalidEmailException : ResponseStatusException(
    HttpStatus.BAD_REQUEST,
    "Invalid email format"
)
