package engine.http.response

import org.apache.catalina.connector.Response
import java.time.LocalDateTime

class CompletionResponse(val id: Long?, val completedAt: LocalDateTime):engine.http.response.Response