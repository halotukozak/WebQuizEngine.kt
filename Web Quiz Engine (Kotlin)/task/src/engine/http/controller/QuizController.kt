package engine.http.controller

import engine.db.model.Question
import engine.db.model.Responseable
import engine.db.model.User
import engine.http.exception.QuestionNotFoundException
import engine.http.request.AnswerRequest
import engine.http.request.QuestionRequest
import engine.http.response.*
import engine.services.QuizService
import engine.services.UserService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/quizzes")
class QuizController(
    private val quizService: QuizService, private val userService: UserService
) {

    @GetMapping
    fun getQuestions(@RequestParam(defaultValue = "0") page: Int): PageResponse = quizService.getAll(page).toResponse()

    @GetMapping("/completed")
    fun getCompletedQuestions(
        @RequestParam(defaultValue = "0") page: Int,
        @AuthenticationPrincipal user: User
    ): PageResponse =
        userService.getCompletedBy(user, page).toResponse()


    @GetMapping("/{id}")
    fun getQuestion(@PathVariable id: Long): QuestionResponse =
        quizService.getQuestionById(id)?.toResponse() ?: throw QuestionNotFoundException()

    @PostMapping("/{id}/solve")
    fun checkAnswer(
        @RequestBody answer: AnswerRequest,
        @PathVariable id: String,
        @AuthenticationPrincipal user: User
    ): AnswerResponse {
        val question = quizService.getQuestionById(id.toLong()) ?: throw QuestionNotFoundException()
        return if (question.check(answer.answer)) {
            userService.completeQuestion(user, question)
            AnswerResponse.ok()
        } else AnswerResponse.wrong()
    }

    @PostMapping
    fun addQuestion(@RequestBody request: QuestionRequest, @AuthenticationPrincipal user: User): QuestionResponse {
        val question = Question.fromRequest(request, user)
        quizService.addAnswer(question)
        return question.toResponse()
    }

    @DeleteMapping("/{id}")
    fun removeQuestion(@PathVariable id: Long, @AuthenticationPrincipal user: User) {
        val question = quizService.getQuestionById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        if (!question.isAuthor(user)) throw ResponseStatusException(HttpStatus.FORBIDDEN)
        quizService.removeQuestion(question)
        throw ResponseStatusException(HttpStatus.NO_CONTENT)
    }
}

private fun <T : Responseable> Page<T>.toResponse(): PageResponse = PageResponse(
    totalPages,
    totalElements,
    isLast,
    isFirst,
    sort,
    number,
    numberOfElements,
    size,
    isEmpty,
    pageable,
    content.map { it.toResponse() })
