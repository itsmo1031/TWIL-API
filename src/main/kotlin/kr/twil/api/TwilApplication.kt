package kr.twil.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TwilApplication

fun main(args: Array<String>) {
    runApplication<TwilApplication>(*args)
}
