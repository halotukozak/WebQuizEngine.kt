package engine.db.model

import engine.http.response.CompletionResponse
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Completion(
    @ManyToOne
    @JoinColumn(name = "user_id")
    private val user: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private val question: Question,
    private val timestamp: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue
    private val id: Long? = null
) : Responseable {
    override fun toResponse(): CompletionResponse = CompletionResponse(question.id(), timestamp)
}
