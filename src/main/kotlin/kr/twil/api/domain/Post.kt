package kr.twil.api.domain

import jakarta.persistence.*

@Entity
class Post(
    author: String,
    title: String,
    content: String
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        private set

    @Column(nullable = false)
    var author: String = author
        private set

    @Column(nullable = false)
    var title: String = title
        private set

    @Column(columnDefinition = "TEXT", nullable=false)
    var content: String = content
        private set
}