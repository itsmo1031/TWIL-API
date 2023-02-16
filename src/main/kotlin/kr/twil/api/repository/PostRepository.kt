package kr.twil.api.repository

import kr.twil.api.domain.Post

interface PostRepository {
    fun save(post: Post): Post
    fun findById(id: Long): Post?
    fun findByTitle(title: String): List<Post?>
    fun findAll(): List<Post>
}