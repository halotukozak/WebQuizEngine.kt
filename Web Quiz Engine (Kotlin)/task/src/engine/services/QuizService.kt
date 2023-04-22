package engine.services

import engine.db.model.Question
import engine.db.model.User
import engine.db.repository.CompletionRepository
import engine.db.repository.QuestionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Service
 class QuizService(
    private val questionRepository: QuestionRepository, private val completionService: CompletionService
) {

    fun getAll(page: Int): Page<Question> = questionRepository.findAll(PageRequest.of(page, 10))

    fun getQuestionById(id: Long): Question? = questionRepository.findByIdOrNull(id)


    fun addAnswer(question: Question): Question {
        questionRepository.save(question)
        return question
    }
    fun removeQuestion(question: Question) {
        questionRepository.delete(question)
    }
}

