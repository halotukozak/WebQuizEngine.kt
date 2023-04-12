package db.model

import http.QuestionRequest
import http.QuestionResponse
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Question(
    @Id
    @GeneratedValue
    private val title: String,
    private val text: String,
    @ElementCollection
    val options: List<String> = listOf(),
    val answer: Int,
    private val id: Long? = null
) {

    fun toResponse(): QuestionResponse = QuestionResponse(id ?: -1, title, text, options)

    companion object {
        fun fromRequest(request: QuestionRequest) =
            Question(request.title, request.text, request.options, request.answer);
    }
}
