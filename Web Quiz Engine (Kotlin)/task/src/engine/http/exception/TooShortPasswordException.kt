package engine.http.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class TooShortPasswordException : ResponseStatusException(
    HttpStatus.BAD_REQUEST,
    "Too short password"
)