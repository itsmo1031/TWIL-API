package kr.twil.api.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import kr.twil.api.domain.Post
import kr.twil.api.repository.MemPostRepository
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PostServiceTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    private val author = "Author"
    private val content = "Content"

    init {
        val memPostRepository: MemPostRepository = mockk()

        @InjectMockKs
        val postService = PostService(memPostRepository)

        afterContainer{
            clearAllMocks()
        }

        this.Given("포스트의 타이틀이 공백으로 주어졌을 때") {
            When("유저가 제출한다면") {
                Then("Exception이 발생한다") {
                    val result = shouldThrow<PostException> {
                        postService.addPost(author, "", content)
                    }

                    result.message shouldBe "Empty Post Title Request"
                }
            }
        }
        this.Given("포스트가 정상적으로 주어졌을 때") {
            val post = Post(author, "Title", content)
            every {
                memPostRepository.save(match {
                    it.author == author
                            && it.title == "Title"
                            && it.content == content
                })
            } returns post
            When("유저가 제출한다면") {
                val result = postService.addPost(author, "Title", content)
                Then("PostResponse의 Title은 Title이어야 한다") {
                    result.title shouldBe "Title"
                }
                Then("PostResponse의 id는 0이어야 한다") {
                    result.id shouldBe 0L
                }
                verify(exactly = 1) {
                    memPostRepository.save(any())
                }
            }
        }
    }
}