package kr.twil.api.service

import kr.twil.api.domain.Post
import kr.twil.api.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired

class PostResponse(
    val id: Long,
    val title: String
)

class PostService(@Autowired private val postRepository: PostRepository){
    fun addPost(author: String,
                title: String,
                content: String): PostResponse {
        if (title==""){
            throw PostException(PostErrorResult.EMPTY_POST_TITLE)
        }
        val post = Post(author, title, content)
        val result = postRepository.save(post)

        return PostResponse(result.id, result.title)
    }
}