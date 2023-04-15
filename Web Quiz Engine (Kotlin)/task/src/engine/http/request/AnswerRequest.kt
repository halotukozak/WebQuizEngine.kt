package engine.http.request

data class AnswerRequest(val answer: String) {
    fun number(): Int = answer.toInt()
}
