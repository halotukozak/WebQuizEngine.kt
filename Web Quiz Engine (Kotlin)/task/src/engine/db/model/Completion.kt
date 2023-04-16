package engine.db.model

import engine.http.response.CompletionResponse
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Completion(
    @OneToOne
    private val user: User,
    @OneToOne
    private val question: Question,
    private val timestamp: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue
    private val id: Long? = null
) {
    fun toResponse(): CompletionResponse = CompletionResponse(question.id(), timestamp)
}
