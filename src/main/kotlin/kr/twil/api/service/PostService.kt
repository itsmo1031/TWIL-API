package kr.twil.api.service

import kr.twil.api.domain.Post

class PostService {
    fun addPost(post: Post): Long {
        if (post.title==""){
            throw PostException(PostErrorResult.EMPTY_POST_TITLE)
        }
        return post.id
    }
}