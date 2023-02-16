package kr.twil.api.service

class PostException(errorResult:PostErrorResult): RuntimeException(errorResult.message) {
}