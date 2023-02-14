package kr.twil.api

import jakarta.persistence.EntityManager
import kr.twil.api.repository.PostRepository
import kr.twil.api.repository.MemPostRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig(val em: EntityManager) {
    @Bean
    fun postRepository(): PostRepository {
        return MemPostRepository(em)
    }
}