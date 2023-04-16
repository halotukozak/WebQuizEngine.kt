package engine.db.repository;

import engine.db.model.Question
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface QuestionRepository : CrudRepository<Question, Long> {
}
