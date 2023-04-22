package engine.services

import engine.db.model.Completion
import engine.db.model.Question
import engine.db.model.User
import engine.db.repository.CompletionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
 class CompletionService(private val completionRepository: CompletionRepository) {

    fun addCompletion(completion: Completion) {
        completionRepository.save(completion)
    }

    fun findByUser(user: User, page: Int): Page<Completion> =
        completionRepository.findAllByUser(user, PageRequest.of(page, 10, Sort.by("timestamp").descending()))

    fun removeByQuestion(question: Question) = completionRepository.deleteAllByQuestion(question)


}
