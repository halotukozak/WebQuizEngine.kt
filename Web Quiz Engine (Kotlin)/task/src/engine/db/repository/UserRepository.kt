package engine.db.repository

import engine.db.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsUserByEmail(email: String): Boolean
}