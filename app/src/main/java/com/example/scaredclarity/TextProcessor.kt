package com.example.scaredclarity

private val stopWords = setOf(
    "the","is","and","a","an","of","to","in","on","for","with",
    "about","this","that","it","be","are","was","were","as","by"
)

fun preprocess(text: String): List<String> {

    return text
        .lowercase()
        .replace(Regex("[^a-z ]"), "")
        .split(" ")
        .map { stem(it) }
        .filter { it.isNotBlank() && it !in stopWords }
}

fun stem(word: String): String {

    return when {
        word.endsWith("ing") -> word.removeSuffix("ing")
        word.endsWith("ed") -> word.removeSuffix("ed")
        word.endsWith("ly") -> word.removeSuffix("ly")
        word.endsWith("es") -> word.removeSuffix("es")
        word.endsWith("s") && word.length > 3 -> word.removeSuffix("s")
        else -> word
    }
}