package http

data class QuestionRequest(val title: String, val text: String, val options: List<String>, val answer: Int)
