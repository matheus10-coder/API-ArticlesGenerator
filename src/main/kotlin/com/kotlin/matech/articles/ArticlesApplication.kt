package com.kotlin.matech.articles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ArticlesApplication

fun main(args: Array<String>) {
	runApplication<ArticlesApplication>(*args)
}
