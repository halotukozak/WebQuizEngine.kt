package engine.http.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class QuestionRequest(
    @NotBlank val title: String,
    @NotBlank val text: String,
    @Size(min = 2) val options: List<String>,
    val answer: Set<Int>?
)
