package engine.db.model

import engine.http.request.RegisterRequest
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class User(private val email: String, private val password: String, @Id @GeneratedValue val id: Long? = null) :
    UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = HashSet()


    override fun getPassword(): String = password
    override fun getUsername(): String = email
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
