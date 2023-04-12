package http

data class QuestionResponse(val id: Long, val title: String, val text: String, val options: List<String>)
