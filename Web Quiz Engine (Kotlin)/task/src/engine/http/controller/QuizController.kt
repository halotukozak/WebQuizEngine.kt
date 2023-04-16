package engine.http.controller

import engine.db.model.Question
import engine.db.model.User
import engine.http.exception.QuestionNotFoundException
import engine.http.request.AnswerRequest
import engine.http.request.QuestionRequest
import engine.http.response.AnswerResponse
import engine.http.response.QuestionResponse
import engine.services.QuizService
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/quizzes")
class QuizController(
    private val service: QuizService,
) {

    @GetMapping
    fun getQuestions(): List<QuestionResponse> = service.getAll().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getQuestion(@PathVariable id: Long): QuestionResponse =
        service.getQuestionById(id)?.toResponse() ?: throw QuestionNotFoundException()

    @PostMapping("/{id}/solve")
    fun checkAnswer(@RequestBody answer: AnswerRequest, @PathVariable id: String): AnswerResponse {
        val question = service.getQuestionById(id.toLong()) ?: throw QuestionNotFoundException()
        return if (question.check(answer.answer)) AnswerResponse.ok()
        else AnswerResponse.wrong()
    }

    @PostMapping
    fun addQuestion(@RequestBody request: QuestionRequest, @AuthenticationPrincipal user: User): QuestionResponse {
        val question = Question.fromRequest(request, user)
        service.addAnswer(question)
        return question.toResponse()
    }

    @DeleteMapping("/{id}")
    fun removeQuestion(@PathVariable id: Long, @AuthenticationPrincipal user: User) {
        val question = service.getQuestionById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        if (!question.isAuthor(user)) throw ResponseStatusException(HttpStatus.FORBIDDEN)
        service.removeQuestion(question)
        throw ResponseStatusException(HttpStatus.NO_CONTENT)
    }
}
