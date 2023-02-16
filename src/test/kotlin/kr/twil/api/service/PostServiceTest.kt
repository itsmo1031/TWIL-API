package kr.twil.api.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kr.twil.api.domain.Post
import kr.twil.api.repository.MemPostRepository
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PostServiceTest :BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    private val author = "Author"
    private val content = "Content"

    @InjectMockKs
    private val target = PostService()

    private val memPostRepository: MemPostRepository = mockk()

    init{
        this.Given("포스트의 타이틀이 공백으로 주어졌을 때"){
            When("유저가 제출한다면"){
                Then("Exception이 발생한다"){
                    val result = shouldThrow<PostException>{
                        target.addPost(Post(author,"",content))}

                    result.message shouldBe "Empty Post Title Request"
                }
            }
        }

    }
}