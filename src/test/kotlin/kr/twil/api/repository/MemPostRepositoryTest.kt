package kr.twil.api.repository

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
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
        this.Given("Post의 정보가"){
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

        this.Given("Post의 id값이"){
            When("주어졌을 때"){
                Then("올바르게 검색되어야 함"){
                    val post = Post(
                        author = "Author",
                        title = "Title",
                        content = "Content"
                    )
                    memPostRepository.save(post)
                    val result = memPostRepository.findById(post.id)

                    result shouldNotBe null
                    result?.id shouldBe post.id
                }
            }
        }

        this.Given("Post의 Title이"){
            When("주어졌을 때"){
                Then("Title을 가진 모든 Post가 검색되어야 함"){
                    val post = Post(
                        author = "Author",
                        title = "Title",
                        content = "Content"
                    )
                    val post2 = Post(
                        author = "Author2",
                        title = "Another Title is Here",
                        content = "Another Content"
                    )
                    val searchWord = "title"
                    memPostRepository.save(post)
                    memPostRepository.save(post2)
                    val result = memPostRepository.findByTitle(searchWord)

                    result.forAll {
                        it?.title shouldContain searchWord
                    }
                }
            }
        }
    }
}