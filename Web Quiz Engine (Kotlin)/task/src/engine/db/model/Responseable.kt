package engine.db.model

import engine.http.response.Response
interface Responseable {
    fun toResponse(): Response
}