package com.kotlin.matech.articles

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

//This is a file because I have the flexibility to create multiple controllers

@RestController
@RequestMapping("/matech/v1/articles")
//Add the repository at constructor
class ArticleController (val repository: ArticleRepository){
    //In memory list, but we want to change for a database
    //val articles = mutableListOf(Article("My Title", "my content"))

    //This is our getter generate the articles (pull all of them)
    /*@GetMapping
    fun articles(): MutableList<Article> {
        return articles
    } // or you can do inline -> fun articles() = articles (this will already instantiate)
     */
    //new
    @GetMapping
    fun article() = repository.findAllByOrderByCreatedAtDesc()


    //This will fetch a specific article if it does not find it should through 404
    /*@GetMapping("/{slug}")
    fun articles(@PathVariable slug: String) =
        articles.find { article -> article.slug == slug } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
     */
    //new
    @GetMapping("/{slug}")
    fun articles(@PathVariable slug: String) = repository.findBySlug(slug).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND)}


    /*//Add the article
    @PostMapping
    fun newArticle(@RequestBody article: Article):Article{
        articles.add(article)
        return article
    }
     */
    //new
    @PostMapping
    fun newArticle(@RequestBody article: Article):Article{
        article.id = null
        repository.save(article)
        return article
    }


    /*//Update by title (same as add)
    @PutMapping("/{slug}")
    fun updateArticle(@RequestBody article: Article, @PathVariable slug: String):Article {
        val existingArticle = articles.find { it.slug == slug } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        existingArticle.content = article.content
        return article
    }
    */
    //new
    @PutMapping("/{slug}")
    fun updateArticle(@RequestBody article: Article, @PathVariable slug: String):Article {
        val existingArticle = repository.findBySlug(slug).orElseThrow {throw ResponseStatusException(HttpStatus.NOT_FOUND)}
        existingArticle.content = article.content
        repository.save(article)
        return article
    }

    /*//Delete current record
    @DeleteMapping("/{slug}")
    fun deleteArticle(@PathVariable slug: String) {
        articles.removeIf { article -> article.slug == slug }
    }
     */
    //new
    @DeleteMapping("/{slug}")
    fun deleteArticle(@PathVariable slug: String) {
        val existingArticle = repository.findBySlug(slug).orElseThrow {throw ResponseStatusException(HttpStatus.NOT_FOUND)}
        repository.delete(existingArticle)

    }


}






















