package engine.services

import engine.db.model.Question
import org.springframework.stereotype.Service

@Service
class QuizService{

    private val repository = mutableListOf<Question>()
    fun getAll(): List<Question> = repository

    fun getQuestionById(id: Long): Question? = repository.firstOrNull{it.getId() == id}


    fun addAnswer(question: Question): Question {
        repository.add(question)
        return question
    }
}

