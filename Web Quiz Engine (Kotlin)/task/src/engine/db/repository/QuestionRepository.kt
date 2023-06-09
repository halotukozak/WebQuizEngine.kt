package engine.db.repository;

import engine.db.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
}
