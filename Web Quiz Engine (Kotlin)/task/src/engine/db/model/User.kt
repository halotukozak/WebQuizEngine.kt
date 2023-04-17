package engine.db.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
class User(private val email: String, private val password: String, @Id @GeneratedValue val id: Long? = null) :
    UserDetails {

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val completed: MutableCollection<Completion> = mutableSetOf()

    fun completeQuestion(completedQuestion: Completion) = completed.add(completedQuestion)
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = HashSet()
    override fun getPassword(): String = password
    override fun getUsername(): String = email
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
