package kr.twil.api.repository

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kr.twil.api.domain.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemPostRepositoryTest(
    @Autowired private val memPostRepository: MemPostRepository
): BehaviorSpec(){
    override fun extensions() = listOf(SpringExtension)

    init{
        this.Given("Content의 정보가"){
            When("주어졌을 때"){
                Then("정상적으로 등록되어야 함"){
                    val post = Post(
                        author = "Author",
                        title = "Title",
                        content = "Content"
                    )
                    val result = memPostRepository.save(post)

                    result.id shouldNotBe null
                    result.author shouldBe "Author"
                    result.title shouldBe "Title"
                    result.content shouldBe "Content"
                }
            }
        }
    }
}