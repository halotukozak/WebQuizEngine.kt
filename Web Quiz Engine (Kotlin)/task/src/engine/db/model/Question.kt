package engine.db.model

import engine.http.request.QuestionRequest
import engine.http.response.QuestionResponse

data class Question(
    private val title: String,
    private val text: String,
    private val options: List<String> = listOf(),
    private val answer: List<Int>,
    private val id: Long
) {

    fun toResponse(): QuestionResponse = QuestionResponse(id, title, text, options)
    fun check(answers: List<Int>): Boolean = answer == answers
    fun getId() = id

    companion object {
        private var lastId = 1L
        fun fromRequest(request: QuestionRequest) =
            Question(request.title, request.text, request.options, request.answer ?: emptyList(), lastId++)
    }
}
