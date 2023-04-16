package engine.services

import engine.db.model.Completion
import engine.db.model.Question
import engine.db.model.User
import engine.db.repository.CompletionRepository
import engine.db.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val completionRepository: CompletionRepository) :
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
        completionRepository.save(completion)
        user.completeQuestion(completion)
        userRepository.save(user)
    }
}