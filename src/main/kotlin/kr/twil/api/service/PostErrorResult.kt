package kr.twil.api.service

import org.springframework.http.HttpStatus

enum class PostErrorResult(val httpStatus: HttpStatus,  val message: String) {
    EMPTY_POST_TITLE(HttpStatus.BAD_REQUEST, "Empty Post Title Request"),
    ;
}