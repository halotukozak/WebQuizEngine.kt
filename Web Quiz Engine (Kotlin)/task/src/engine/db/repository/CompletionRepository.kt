package engine.db.repository

import engine.db.model.Completion
import engine.db.model.Question
import engine.db.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompletionRepository : JpaRepository<Completion, Long> {
    fun findAllByUser(user: User, pageable: Pageable): Page<Completion>
    fun deleteAllByQuestion(question: Question)
}