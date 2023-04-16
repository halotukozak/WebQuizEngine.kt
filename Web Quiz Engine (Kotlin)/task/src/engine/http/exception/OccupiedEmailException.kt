package engine.http.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class OccupiedEmailException : ResponseStatusException(
    HttpStatus.BAD_REQUEST,
    "This email is already occupied"
)
