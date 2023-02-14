package kr.twil.api.repository

import jakarta.persistence.EntityManager
import kr.twil.api.domain.Post
import org.springframework.stereotype.Repository

@Repository
class MemPostRepository(private val em: EntityManager): PostRepository {
    override fun save(post: Post): Post {
        em.persist(post)
        return post
    }

    override fun findById(id: Long): Post? {
        TODO("Not yet implemented")
    }

    override fun findByTitle(title: String): Post? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Post> {
        TODO("Not yet implemented")
    }
}