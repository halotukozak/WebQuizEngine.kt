package engine.http.response

class CompletionPageResponse(
    val totalPages: Int,
    val totalElements: Int,
    val first: Boolean,
    val last: Boolean,
    val empty: Boolean,
    val content: List<CompletionResponse>
):Response