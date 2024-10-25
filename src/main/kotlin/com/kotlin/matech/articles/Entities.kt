package com.kotlin.matech.articles

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Article(
    //(?)question mark make the value nullable
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) var id: Long? = null,
    var title: String,
    var content: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),

    //https://blog/my-first-title -> slug (turning title into a slug)
    var slug: String = title.toSlug()
)