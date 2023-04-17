package engine.db.model

import engine.http.response.CompletionResponse
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Completion(
    @ManyToOne
    private val user: User,
    @ManyToOne
    private val question: Question,
    private val timestamp: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue
    private val id: Long? = null
) : Responseable {
    override fun toResponse(): CompletionResponse = CompletionResponse(question.id(), timestamp)
}
