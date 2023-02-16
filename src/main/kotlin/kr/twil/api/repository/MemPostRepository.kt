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
        return em.find(Post::class.java, id)
    }

    override fun findByTitle(title: String): List<Post?> {
        return em.createQuery("SELECT p FROM Post p WHERE p.title LIKE :title",Post::class.java)
            .setParameter("title","%${title}%")
            .resultList
    }

    override fun findAll(): List<Post> {
        TODO("Not yet implemented")
    }
}