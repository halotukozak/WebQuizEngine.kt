package engine.http

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
open class SecurityConfig(private val userDetailsService: UserDetailsService) {
    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic().and().csrf().disable().authorizeRequests()
            .mvcMatchers("/api/register").permitAll()
            .mvcMatchers("/api/**").authenticated()
            .mvcMatchers(HttpMethod.POST, "/actuator/shutdown").permitAll()

        return http.build()
    }

    @Bean
    open fun authProvider(): DaoAuthenticationProvider? {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    @Bean
    open fun authManager(http: HttpSecurity): AuthenticationManager =
        http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .authenticationProvider(authProvider())
            .build()

    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}