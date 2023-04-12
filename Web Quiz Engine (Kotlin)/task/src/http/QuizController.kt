package http

import db.QuestionRepository
import db.model.Question
import org.springframework.web.bind.annotation.*

@RestController
class QuizController(private val repository: QuestionRepository) {

    @GetMapping("/api/quizzes")
    fun getQuestions(): List<QuestionResponse> = repository.findAll().map { it.toResponse() }
    @GetMapping("/api/quizzes/{id}")
    fun getQuestion(@PathVariable id: Long): QuestionResponse = repository.findById(id).get().toResponse()

    @PostMapping("/api/quiz")
    fun postAnswer(@RequestBody answer: String): AnswerResponse =
        if (answer.endsWith("2")) AnswerResponse(true, "Congratulations, you're right!")
        else AnswerResponse(false, "Wrong answer! Please, try again.")

    @PostMapping("/api/quizzes")
    fun addAnswer(@RequestBody request: QuestionRequest): QuestionResponse {
        val question = Question.fromRequest(request)
        repository.save(question)
        return question.toResponse()
    }
}
