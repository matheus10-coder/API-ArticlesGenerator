package com.kotlin.matech.articles

import java.util.*

//Extension functions
fun String.toSlug()= lowercase(Locale.getDefault())
    .replace("\n", "")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")
