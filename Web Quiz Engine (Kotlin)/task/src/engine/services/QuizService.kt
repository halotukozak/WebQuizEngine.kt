package engine.services

import engine.db.model.Question
import engine.db.repository.QuestionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuizService(private val repository: QuestionRepository) {

    fun getAll(): List<Question> = repository.findAll().toList()

    fun getQuestionById(id: Long): Question? = repository.findByIdOrNull(id)


    fun addAnswer(question: Question): Question {
        repository.save(question)
        return question
    }

    fun removeQuestion(question: Question) = repository.delete(question)

}

