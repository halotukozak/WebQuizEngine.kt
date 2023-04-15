package engine.http.response

data class AnswerResponse(val success: Boolean, val feedback: String) {
    companion object {
        fun ok(): AnswerResponse = AnswerResponse(true, "Congratulations, you're right!")
        fun wrong(): AnswerResponse = AnswerResponse(false, "Wrong answer! Please, try again.")
    }
}

