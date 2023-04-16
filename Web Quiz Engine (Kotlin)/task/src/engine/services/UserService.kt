package engine.services

import engine.db.model.User
import engine.db.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) : UserDetailsService {

    fun getUserById(id: Long): User? = repository.findByIdOrNull(id)


    fun addUser(user: User): User {
        repository.save(user)
        return user
    }

    fun existsUserByEmail(email: String): Boolean = repository.existsUserByEmail(email)
    override fun loadUserByUsername(username: String?): UserDetails? = username?.let { repository.findByEmail(it) }
}