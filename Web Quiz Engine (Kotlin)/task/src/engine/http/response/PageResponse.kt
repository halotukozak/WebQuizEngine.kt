package engine.http.response

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

class PageResponse(
    val totalPages: Int,
    val totalElements: Long,
    val last: Boolean,
    val first: Boolean,
    val sort: Sort,
    val number: Int,
    val numberOfElements: Int,
    val size: Int,
    val empty: Boolean,
    val pageable: Pageable,
    val content: List<Response>
)