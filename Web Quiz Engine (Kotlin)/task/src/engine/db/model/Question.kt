package engine.db.model

import engine.http.request.QuestionRequest
import engine.http.response.QuestionResponse
import javax.persistence.*

@Entity
data class Question(
    private var title: String = "",
    private var text: String = "",
    @ElementCollection
    private val options: List<String> = listOf(),
    @ElementCollection
    private val answer: Set<Int> = setOf(),
    @OneToOne
    private val author: User,
    @Id
    @GeneratedValue
    private val id: Long? = null
) {

    fun toResponse(): QuestionResponse = QuestionResponse(id, title, text, options)
    fun check(answers: Set<Int>): Boolean = answer == answers
    fun isAuthor(user: User): Boolean = author.username == user.username

    fun id(): Long? = id

    companion object {
        fun fromRequest(request: QuestionRequest, author: User) =
            Question(request.title, request.text, request.options, request.answer ?: emptySet(), author)
    }
}
