package engine

import http.AnswerResponse
import http.QuestionResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping("/api/quiz")
    fun getQuestion(): QuestionResponse = QuestionResponse(
        "The Java Logo",
        "What is depicted on the Java logo?",
        listOf("Robot", "Tea leaf", "Cup of coffee", "Bug")
    )

    @PostMapping("/api/quiz")
    fun postAnswer(@RequestBody answer: String): AnswerResponse =
        if (answer.endsWith("2")) AnswerResponse(true, "Congratulations, you're right!")
        else AnswerResponse(false, "Wrong answer! Please, try again.")
}
