package engine.http.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class RegisterRequest(
    @NotBlank @field:Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") val email: String,
    @field:Min(5) val password: String
)
