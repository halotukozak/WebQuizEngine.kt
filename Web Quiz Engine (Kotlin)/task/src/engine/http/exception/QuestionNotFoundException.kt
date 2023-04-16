package engine.http.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class QuestionNotFoundException : ResponseStatusException(
    HttpStatus.NOT_FOUND,
    "Question not found"
)