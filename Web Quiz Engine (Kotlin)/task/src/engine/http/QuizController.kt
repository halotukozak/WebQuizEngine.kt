package engine.http

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import engine.db.model.Question
import engine.http.request.AnswerRequest
import engine.http.request.QuestionRequest
import engine.http.response.AnswerResponse
import engine.http.response.QuestionResponse
import engine.services.QuizService
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("/api/quizzes")
class QuizController(private val service: QuizService) {

    @GetMapping
    fun getQuestions(): List<QuestionResponse> = service.getAll().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getQuestion(@PathVariable id: Long): QuestionResponse =
        service.getQuestionById(id)?.toResponse() ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Question not found"
        )

    @PostMapping("/{id}/solve")
    fun checkAnswer(@RequestBody answer: AnswerRequest, @PathVariable id: String): AnswerResponse {
        val question = service.getQuestionById(id.toLong()) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Question not found"
        )

        return if (question.check(answer.number())) AnswerResponse.ok()
        else AnswerResponse.wrong()
    }

    @PostMapping
    fun addAnswer(@RequestBody request: QuestionRequest): QuestionResponse {
        val question = Question.fromRequest(request)
        service.addAnswer(question)
        return question.toResponse()
    }
}
