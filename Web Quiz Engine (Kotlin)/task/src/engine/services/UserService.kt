package engine.services

import engine.db.model.Completion
import engine.db.model.Question
import engine.db.model.User
import engine.db.repository.CompletionRepository
import engine.db.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(private val userRepository: UserRepository, private val completionService: CompletionService) :
    UserDetailsService {

    fun getUserById(id: Long): User? = userRepository.findByIdOrNull(id)


    fun addUser(user: User): User {
        userRepository.save(user)
        return user
    }

    fun existsUserByEmail(email: String): Boolean = userRepository.existsUserByEmail(email)

    override fun loadUserByUsername(username: String?): UserDetails? = username?.let { userRepository.findByEmail(it) }

    fun completeQuestion(user: User, question: Question) {
        val completion = Completion(user, question)
        user.completeQuestion(completion)
        completionService.addCompletion(completion)
        userRepository.save(user)
    }

    fun getCompletedBy(user: User, page: Int): Page<Completion> =
    completionService.findByUser(user, page)

}